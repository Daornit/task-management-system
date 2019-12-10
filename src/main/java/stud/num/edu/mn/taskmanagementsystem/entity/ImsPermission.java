package stud.num.edu.mn.taskmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "IMS_PERMISSION", schema = "IMS")
@SequenceGenerator(name="ImsPermissionSeq", sequenceName = "IMS.SEQ_IMS_PERMISSION", allocationSize = 1, initialValue = 2000)
public class ImsPermission implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "ImsPermissionSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "PERMISSION")
    private String permission;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ROLE_ID")
    private Long roleId;
}
