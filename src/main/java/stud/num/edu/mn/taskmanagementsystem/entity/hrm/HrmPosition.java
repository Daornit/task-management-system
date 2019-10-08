package stud.num.edu.mn.taskmanagementsystem.entity.hrm;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import stud.num.edu.mn.taskmanagementsystem.core.BaseEntity;
import javax.persistence.*;

@Data
@Entity
@Table(name = "HRM_POSITION", schema = "HRM")
@SequenceGenerator(name = "hrmPositionSeq", sequenceName = "HRM.SEQ_HRM_POSITION", allocationSize = 1)
public class HrmPosition extends BaseEntity {
    @Id
    @GeneratedValue(generator = "hrmPositionSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PARENT_ID")
    private Long parentId;

    @OneToOne
    @JoinColumn(name = "ID", referencedColumnName = "PARENT_ID")
    @NotFound(action = NotFoundAction.IGNORE)
    private HrmPosition parentPosition;
}
