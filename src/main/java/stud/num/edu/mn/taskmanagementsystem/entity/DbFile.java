package stud.num.edu.mn.taskmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
/*
@author Bat-orgil
@date 2019-12-01
*/
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FILE_STORE", schema = "IMS")
@SequenceGenerator(name = "fileStoreSeq", sequenceName = "IMS.SEQ_FILE_STORE", allocationSize = 1, initialValue = 2000)
public class DbFile {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private String code;

    @Column(name = "CONTENT_TYPE")
    private String contentType;

    @Column(name = "PATH")
    private String path;

    @Transient
    private byte[] data;

    public DbFile(String fileName, String contentType, String code, String path) {
        this.name = fileName;
        this.contentType = contentType;
        this.code = code;
        this.path = path;
    }
}
