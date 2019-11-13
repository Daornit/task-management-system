package stud.num.edu.mn.taskmanagementsystem.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUser {
    private String userid;
    private String name;
    private String email;
    private String avatar;
    private String title;
    private String address;
    private String phone;
    private Long notifyCount;
    private Long unreadCount;
}
