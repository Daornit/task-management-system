package stud.num.edu.mn.taskmanagementsystem.entity.work;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import stud.num.edu.mn.taskmanagementsystem.core.BaseEntity;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;
import stud.num.edu.mn.taskmanagementsystem.entity.Team;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WORK_SPACE", schema = "IMS")
@SequenceGenerator(name = "workSpaceSeq", sequenceName = "IMS.SEQ_WORK_SPACE", allocationSize = 1, initialValue = 2000)
public class WorkSpace extends BaseEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "workSpaceSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private String code;

    @Column(name = "IS_DELETED")
    private Boolean isDeleted;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "ID")
    @NotFound(action = NotFoundAction.IGNORE)
    private ImsUser owner;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "WORK_SPACE_CODE", referencedColumnName = "CODE")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<WorkPackage> workPackages = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "TEAM_CODE", referencedColumnName = "CODE")
    @NotFound(action = NotFoundAction.IGNORE)
    private Team team;
}
