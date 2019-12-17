package stud.num.edu.mn.taskmanagementsystem.util;

import java.util.UUID;
/*
@author Bat-orgil
@date 2019-12-01
*/
public class TaskCodeGenerator {
    public static String newCode() {
        return UUID.randomUUID().toString();
    }
}
