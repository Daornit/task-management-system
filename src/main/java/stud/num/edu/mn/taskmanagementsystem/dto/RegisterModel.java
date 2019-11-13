package stud.num.edu.mn.taskmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String businessTitle;
}
