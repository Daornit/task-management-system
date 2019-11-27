package stud.num.edu.mn.taskmanagementsystem.entity.work;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import stud.num.edu.mn.taskmanagementsystem.core.BaseEntity;
import stud.num.edu.mn.taskmanagementsystem.entity.Comment;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TASK", schema = "IMS")
@SequenceGenerator(name="taskSeq", sequenceName = "IMS.SEQ_TASK", allocationSize = 1)
//Даалгаварын класс ба даалгавар үүсгэх, устгах зэргийг тодорхойлсон
public class Task extends BaseEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "taskSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "PROCESS")
    private Long process = 0L;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private String code;

    @Column(name = "STATUS")
    private String status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    @Column(name = "IS_DELETED")
    private Boolean isDeleted;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "WORK_PACKAGE_CODE")
    private String workPackageCode;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "ASSIGN_ID", referencedColumnName = "ID")
    @NotFound(action = NotFoundAction.IGNORE)
    private ImsUser assign;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "ID")
    @NotFound(action = NotFoundAction.IGNORE)
    private ImsUser owner;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Task> tasks;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "CODE", referencedColumnName = "CODE")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Comment> comments = new ArrayList<>();

    @Transient
    private Long ownerId;

    @Transient
    private Long assignId;

    public Long getOwnerId(){
        if(ownerId != null) {
            return ownerId;
        }
        if(owner != null) {
            return owner.getId();
        }
        return null;
    }

    public Long getAssignId(){
        if(assignId != null) {
            return assignId;
        }
        if(assign != null) {
            return assign.getId();
        }
        return null;
    }
}
