package stud.num.edu.mn.taskmanagementsystem.entity.work;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import stud.num.edu.mn.taskmanagementsystem.core.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WORK_PACKAGE", schema = "IMS")
@SequenceGenerator(name="workPackageSeq", sequenceName = "IMS.SEQ_WORK_PACKAGE", allocationSize = 1)
public class WorkPackage extends BaseEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "workPackageSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CODE")
    private String code;

    @Column(name = "WORK_SPACE_CODE")
    private String workSpaceCode;

    @Column(name = "IS_DELETED")
    private Boolean isDeleted;

    @Column(name = "OWNER_ID")
    private Long ownerId;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "PROCESS_STATUS")
    private Long processStatus;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "WORK_PACKAGE_CODE", referencedColumnName = "CODE")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Task> tasks;

}
