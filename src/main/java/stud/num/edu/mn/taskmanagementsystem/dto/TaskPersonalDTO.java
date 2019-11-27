package stud.num.edu.mn.taskmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import stud.num.edu.mn.taskmanagementsystem.entity.work.Task;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskPersonalDTO {
    private List<Task> tasks;
}
