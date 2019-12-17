package stud.num.edu.mn.taskmanagementsystem.entity.work;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;
import stud.num.edu.mn.taskmanagementsystem.core.BaseEntity;
import stud.num.edu.mn.taskmanagementsystem.entity.Comment;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/*
@author Bat-orgil
@date 2019-12-01
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "WORK_PACKAGE", schema = "IMS")
@SequenceGenerator(name = "workPackageSeq", sequenceName = "IMS.SEQ_WORK_PACKAGE", allocationSize = 1)
public class WorkPackage extends BaseEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "workPackageSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Length(max = 10485760)
    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CODE")
    private String code;

    @Column(name = "WORK_SPACE_CODE")
    private String workSpaceCode;

    @Column(name = "IS_DELETED")
    private Boolean isDeleted;

    @Column(name = "START_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startDate;

    @Column(name = "DEFAULT_VIEW")
    private String defaultView;

    @Column(name = "END_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String endDate;

    @Column(name = "PROCESS_STATUS")
    private Long processStatus;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "ID")
    @NotFound(action = NotFoundAction.IGNORE)
    private ImsUser owner;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "WORK_PACKAGE_CODE", referencedColumnName = "CODE")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Task> tasks;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "CODE", referencedColumnName = "CODE")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Comment> comments = new ArrayList<>();

    @Transient
    private Long ownerId;

    public Long getOwnerId() {
        if (ownerId != null) {
            return ownerId;
        }
        if (owner != null) {
            return owner.getId();
        }
        return null;
    }
}
