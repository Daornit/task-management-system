package stud.num.edu.mn.taskmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkPackage;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkSpaceDTO {
    private Long id;
    private String name;
    private String code;
    private Boolean isDeleted;
    private ImsUser owner;
    private List<WorkPackage> workPackages;
    private HrmTeamDTO team;
}
