package stud.num.edu.mn.taskmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import stud.num.edu.mn.taskmanagementsystem.entity.UserView;

import java.util.List;
/*
@author Bat-orgil
@date 2019-12-01
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkSpaceCreateDTO {
    private String name;
    private List<UserView> members;
}
