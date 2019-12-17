package stud.num.edu.mn.taskmanagementsystem.util;

import java.util.UUID;

public class TaskCodeGenerator {
    public static String newCode() {
        return UUID.randomUUID().toString();
    }
}
