package stud.num.edu.mn.taskmanagementsystem.entity.hrm;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import stud.num.edu.mn.taskmanagementsystem.core.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "HRM_WORKER", schema = "HRM")
@SequenceGenerator(name = "hrmWorkerSeq", sequenceName = "HRM.SEQ_HRM_WORKER", allocationSize = 1 )
public class HrmWorker extends BaseEntity {
    @Id
    @GeneratedValue(generator = "hrmWorkerSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TEAM_CODE")
    private String teamCode;

    @Column(name = "BIRTH_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;

    @OneToOne
    @JoinColumn(name = "ID", referencedColumnName = "POSITION_ID")
    @NotFound(action = NotFoundAction.IGNORE)
    private HrmPosition parentPosition;
}
