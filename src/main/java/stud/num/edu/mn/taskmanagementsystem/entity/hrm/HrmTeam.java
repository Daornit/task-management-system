package stud.num.edu.mn.taskmanagementsystem.entity.hrm;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import stud.num.edu.mn.taskmanagementsystem.core.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "HRM_TEAM", schema = "HRM")
@SequenceGenerator(name = "hrmTeamSeq", sequenceName = "HRM.SEQ_HRM_TEAM", allocationSize = 1)
public class HrmTeam extends BaseEntity {
    @Id
    @GeneratedValue(generator = "hrmTeamSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name ="TEAM_LEADER")
    private Long teamLeader;

    @OneToOne
    @JoinColumn(name = "ID", referencedColumnName = "TEAM_LEADER")
    @NotFound(action = NotFoundAction.IGNORE)
    private HrmWorker leaderWorker;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "TEAM_CODE", referencedColumnName = "CODE")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<HrmWorker> members;

}
