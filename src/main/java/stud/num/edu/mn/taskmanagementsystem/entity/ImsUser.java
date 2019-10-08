package stud.num.edu.mn.taskmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "USER", schema = "IMS")
@RestResource
@SequenceGenerator(name="ImsUserSeq", sequenceName = "IMS.SEQ_USER", allocationSize = 1)
public class ImsUser implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "ImsUserSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    @JsonIgnore
    private String password;

    @Column(name = "ENABLED")
    private boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
    private List<ImsRole> roles;
}
