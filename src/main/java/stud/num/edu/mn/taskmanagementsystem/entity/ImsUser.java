package stud.num.edu.mn.taskmanagementsystem.entity;

import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "USER", schema = "IMS")
@RestResource
@SequenceGenerator(name="ImsUserSeq", sequenceName = "IMS.SEQ_USER", allocationSize = 1, initialValue = 1000    )
public class ImsUser implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "ImsUserSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "BUSINESS_TITLE")
    private String title;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ENABLED")
    private boolean enabled;

    @Column(name = "AVATAR")
    private String avatar;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "TEAM_CODE")
    private String teamCode;

    @Column(name = "GROUP_CODE")
    private String groupCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role")
    private ImsRole role;

    public String getUsername(){
        if(this.lastName != null && this.lastName.length() > 0 && this.firstName != null) {
            return lastName.substring(0,1).toUpperCase() + ". " + firstName;
        }
        return "Default";
    }
}
