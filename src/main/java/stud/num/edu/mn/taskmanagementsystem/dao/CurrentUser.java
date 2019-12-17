package stud.num.edu.mn.taskmanagementsystem.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
@author Bat-orgil
@date 2019-12-01
*/
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
