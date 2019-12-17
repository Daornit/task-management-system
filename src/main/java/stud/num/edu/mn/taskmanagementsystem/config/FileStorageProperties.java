package stud.num.edu.mn.taskmanagementsystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
/*
@author Bat-orgil
@date 2019-12-01
*/
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}