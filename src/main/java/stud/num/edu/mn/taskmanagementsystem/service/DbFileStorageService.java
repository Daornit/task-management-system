package stud.num.edu.mn.taskmanagementsystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import stud.num.edu.mn.taskmanagementsystem.config.FileStorageProperties;
import stud.num.edu.mn.taskmanagementsystem.dao.FileDAO;
import stud.num.edu.mn.taskmanagementsystem.dao.WorkPackageDAO;
import stud.num.edu.mn.taskmanagementsystem.dto.PackageFileTreeDTO;
import stud.num.edu.mn.taskmanagementsystem.entity.DbFile;
import stud.num.edu.mn.taskmanagementsystem.entity.work.Task;
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkPackage;
import stud.num.edu.mn.taskmanagementsystem.exceptions.FileNotFoundException;
import stud.num.edu.mn.taskmanagementsystem.exceptions.FileStorageException;
import stud.num.edu.mn.taskmanagementsystem.util.TaskCodeGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class DbFileStorageService {

    private final Path fileStorageLocation;
    private Logger log = LoggerFactory.getLogger(DbFileStorageService.class);
    @Autowired
    private FileDAO fileDAO;
    @Autowired
    private WorkPackageDAO workPackageDAO;

    @Autowired
    public DbFileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new FileStorageException
                    ("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    private static byte[] readBytesFromFile(String filePath) {
        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;

        try {
            File file = new File(filePath);
            bytesArray = new byte[(int) file.length()];

            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytesArray;
    }

    public DbFile storeFile(MultipartFile file, String code) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry FileName contains invalid path sequence " + fileName);
            }
            Path targetLocation = this.fileStorageLocation.resolve(TaskCodeGenerator.newCode());
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileDAO.save(new DbFile(fileName, file.getContentType(), code, targetLocation.toString()));
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public DbFile getFile(String fileId) {
        DbFile dbFile = fileDAO.findById(fileId).orElseThrow(() -> new FileNotFoundException("File not found with id: " + fileId));
        dbFile.setData(readBytesFromFile(dbFile.getPath()));
        return dbFile;
    }

    public List<DbFile> getFileByCode(String code) {
        List<DbFile> dbFile = fileDAO.findAllByCode(code);
        return dbFile;
    }

    public PackageFileTreeDTO getFileByPackageCode(String code) {
        WorkPackage workPackage = workPackageDAO.findByCode(code);
        PackageFileTreeDTO tree = new PackageFileTreeDTO(workPackage.getId() + "", workPackage.getName(), new ArrayList<>(), false);
        for (Task task : workPackage.getTasks()) {
            if (!task.getIsDeleted()) {
                List<DbFile> dbFile = fileDAO.findAllByCode(task.getCode());
                PackageFileTreeDTO childTree = new PackageFileTreeDTO(task.getId() + "", task.getName(), new ArrayList<>(), false);
                for (DbFile file : dbFile) {
                    PackageFileTreeDTO leaf = new PackageFileTreeDTO(file.getId(), file.getName(), new ArrayList<>(), true);
                    childTree.getChildren().add(leaf);
                }
                tree.getChildren().add(childTree);
            }
        }
        return tree;
    }
}
