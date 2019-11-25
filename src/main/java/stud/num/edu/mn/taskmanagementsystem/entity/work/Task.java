package stud.num.edu.mn.taskmanagementsystem.entity.work;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import stud.num.edu.mn.taskmanagementsystem.core.BaseEntity;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TASK", schema = "IMS")
@SequenceGenerator(name="taskSeq", sequenceName = "IMS.SEQ_TASK", allocationSize = 1)
public class Task extends BaseEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "taskSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @Column(name = "PROCESS")
    private Long process;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IS_DELETED")
    private Boolean isDeleted;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "WORK_PACKAGE_CODE")
    private String workPackageCode;

    @OneToMany()
    @JoinTable(name = "task_assign",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "ims_user_id")
    )
    private Set<ImsUser> assigns = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Task> tasks;
}
