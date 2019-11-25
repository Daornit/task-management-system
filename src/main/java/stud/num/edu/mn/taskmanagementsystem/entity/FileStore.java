package stud.num.edu.mn.taskmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FILE_STORE", schema = "IMS")
@SequenceGenerator(name="fileStoreSeq", sequenceName = "IMS.SEQ_FILE_STORE", allocationSize = 1)
public class FileStore {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "fileStoreSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PATH")
    private String path;
}
