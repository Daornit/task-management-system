package stud.num.edu.mn.taskmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HrmTeamDTO {
    private Long id;
    private String code;
    private String workSpaceCode;
    private List<IdModel> members;
}
