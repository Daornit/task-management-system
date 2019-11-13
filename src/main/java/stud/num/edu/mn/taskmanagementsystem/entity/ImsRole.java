package stud.num.edu.mn.taskmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROLE", schema = "IMS")
@SequenceGenerator(name="ImsRoleSeq", sequenceName = "IMS.SEQ_ROLE", allocationSize = 1)
public class ImsRole implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "ImsRoleSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    private List<ImsPermission> permissions;
}
