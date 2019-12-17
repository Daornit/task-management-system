package stud.num.edu.mn.taskmanagementsystem.entity;

import lombok.Data;
import org.hibernate.annotations.Subselect;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.io.Serializable;
/*
@author Bat-orgil
@date 2019-12-01
*/
@Data
@Entity
@Subselect("SELECT us.id, us.email, us.avatar, us.enabled, us.first_name, us.last_name, us.phone, rl.role, us.group_code FROM IMS.user us\n" +
        "INNER JOIN IMS.role rl ON us.role = rl.id")
@RestResource
public class UserView implements Serializable {
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

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ENABLED")
    private boolean enabled;

    @Column(name = "AVATAR")
    private String avatar;

    @Column(name = "GROUP_CODE")
    private String groupCode;

    @Column(name = "ROLE")
    private String role;

    public String getUsername() {
        if (this.lastName != null && this.lastName.length() > 0 && this.firstName != null) {
            return lastName.substring(0, 1).toUpperCase() + ". " + firstName;
        }
        return "Default";
    }
}
