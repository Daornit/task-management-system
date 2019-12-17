package stud.num.edu.mn.taskmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "USER", schema = "IMS")
@RestResource
@SequenceGenerator(name = "ImsUserSeq", sequenceName = "IMS.SEQ_USER", allocationSize = 1, initialValue = 2000)
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

    @Column(name = "GROUP_CODE")
    private String groupCode;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role")
    private ImsRole role;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        if (this.lastName != null && this.lastName.length() > 0 && this.firstName != null) {
            return lastName.substring(0, 1).toUpperCase() + ". " + firstName;
        }
        return "Default";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImsUser user = (ImsUser) o;
        return Objects.equals(id, user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
