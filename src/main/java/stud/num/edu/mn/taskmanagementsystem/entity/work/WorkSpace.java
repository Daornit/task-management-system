package stud.num.edu.mn.taskmanagementsystem.entity.work;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import stud.num.edu.mn.taskmanagementsystem.core.BaseEntity;
import stud.num.edu.mn.taskmanagementsystem.entity.hrm.HrmTeam;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WORK_SPACE", schema = "IMS")
@SequenceGenerator(name="workSpaceSeq", sequenceName = "IMS.SEQ_WORK_SPACE", allocationSize = 1)
public class WorkSpace extends BaseEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "workSpaceSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private String code;

    @Column(name = "TEAM_CODE")
    private Boolean teamCode;

    @Column(name = "IS_DELETED")
    private Boolean isDeleted;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "WORK_SPACE_CODE", referencedColumnName = "CODE")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<WorkPackage> workPackages;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "WORK_SPACE_CODE", referencedColumnName = "CODE")
    @NotFound(action = NotFoundAction.IGNORE)
    private HrmTeam team;
}
