package stud.num.edu.mn.taskmanagementsystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;
/*
@author Bat-orgil
@date 2019-12-01
*/
@Data
@Entity
@Table(name = "CONFIRMATION_TOKEN", schema = "IMS")
@NoArgsConstructor
@SequenceGenerator(name = "confirmationTokenSeq", sequenceName = "HRM.SEQ_CONFIRMATION_TOKEN", allocationSize = 1, initialValue = 1000)
public class ConfirmationToken {

    @Id
    @GeneratedValue(generator = "confirmationTokenSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "token_id")
    private long tokenId;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Column(name = "active")
    private Boolean active;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = ImsUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "ID")
    private ImsUser user;

    public ConfirmationToken(ImsUser user) {
        this.user = user;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }
}
