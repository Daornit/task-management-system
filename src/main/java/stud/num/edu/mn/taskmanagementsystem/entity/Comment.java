package stud.num.edu.mn.taskmanagementsystem.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMMENT", schema = "IMS")
@SequenceGenerator(name="commentSeq", sequenceName = "IMS.SEQ_COMMENT", allocationSize = 1, initialValue = 2000)
public class Comment implements Serializable, Comparable<Comment> {
    @Id
    @GeneratedValue(generator = "commentSeq", strategy = GenerationType.SEQUENCE)
    @Column(name="ID")
    private Long id;

    @Column(name="CODE")
    private String code;

    @Column(name="CONTENT")
    private String content;

    @Column(name="OWNER")
    private ImsUser owner;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date datetime;

    public String getAvatar(){
        return owner.getAvatar();
    }
    public String getAuthor(){
        return owner.getUsername();
    }

    @Override
    public int compareTo(Comment comment) {
        if (getDatetime() == null || comment.getDatetime() == null) {
            return 0;
        }
        return getDatetime().compareTo(comment.getDatetime());
    }
}
