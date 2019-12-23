package stud.num.edu.mn.taskmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkPackage;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//Сэтгэгдэл үлдээх хэсэгт байрлах обьектууд
public class CommentDTO {
    private String id;
    private Long author;
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date datetime;
}
