package stud.num.edu.mn.taskmanagementsystem.entity;

import lombok.Data;
import stud.num.edu.mn.taskmanagementsystem.core.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "TEAM", schema = "IMS")
@SequenceGenerator(name = "teamSeq", sequenceName = "HRM.SEQ_IMS_TEAM", allocationSize = 1, initialValue = 2000)
public class Team extends BaseEntity {
    @Id
    @GeneratedValue(generator = "teamSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "WORK_SPACE_CODE")
    private String workSpaceCode;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "team_member",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "ims_user_id")
    )
    private Set<ImsUser> members = new HashSet<>();

    public void addUser(ImsUser user) {
        members.add(user);
    }

    public void removeUser(ImsUser user) {
        members.remove(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        return id != null && id.equals(((Team) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
