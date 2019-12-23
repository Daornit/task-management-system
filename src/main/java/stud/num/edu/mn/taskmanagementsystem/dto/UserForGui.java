package stud.num.edu.mn.taskmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsRole;

import java.io.Serializable;
import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForGui  implements Serializable {
    private Long id;
    private String avatar;
    private String username;
    private String groupCode;
    private HashMap<String, String> permissions;
}
