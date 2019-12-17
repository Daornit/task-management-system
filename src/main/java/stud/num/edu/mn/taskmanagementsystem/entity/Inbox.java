package stud.num.edu.mn.taskmanagementsystem.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "INBOX", schema = "IMS")
@SequenceGenerator(name = "inboxSeq", sequenceName = "IMS.SEQ_INBOX", allocationSize = 1, initialValue = 2000)
public class Inbox implements Serializable {
    @Id
    @GeneratedValue(generator = "inboxSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "LINK")
    private String link;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "NOTIFIED")
    private Boolean notified;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @NotFound(action = NotFoundAction.IGNORE)
    private ImsUser user;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
}
