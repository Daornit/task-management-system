package stud.num.edu.mn.taskmanagementsystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import stud.num.edu.mn.taskmanagementsystem.dto.UploadFileResponse;
import stud.num.edu.mn.taskmanagementsystem.entity.DbFile;
import stud.num.edu.mn.taskmanagementsystem.service.DbFileStorageService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private DbFileStorageService dbFileStorageService;

    @PostMapping("/upload/{code}")
    public UploadFileResponse upload(@RequestParam("file") MultipartFile file, @PathVariable String code) {
        DbFile f = dbFileStorageService.storeFile(file, code);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/download/")
                .path(f.getId())
                .toUriString();

        return new UploadFileResponse(f.getName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadFiles/{code}")
    public List<UploadFileResponse> uploadFiles(@RequestParam("files") MultipartFile[] files, @PathVariable String code) {
        return Arrays.asList(files)
                .stream()
                .map(file -> upload(file, code))
                .collect(Collectors.toList());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity download(@PathVariable String fileId) {
        DbFile dbFile = dbFileStorageService.getFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getName() + "\"")
                .body(dbFile.getData());
    }

    @GetMapping("/fileList/{code}")
    public ResponseEntity getByCode(@PathVariable String code) {
        return ResponseEntity.ok(dbFileStorageService.getFileByCode(code));
    }

    @GetMapping("/fileByPackage/{code}")
    public ResponseEntity getByPackageCode(@PathVariable String code) {
        return ResponseEntity.ok(dbFileStorageService.getFileByPackageCode(code));
    }
}
