package stud.num.edu.mn.taskmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import stud.num.edu.mn.taskmanagementsystem.entity.work.Task;

import java.util.List;
/*
@author Bat-orgil
@date 2019-12-01
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageFileTreeDTO {
    private String key;
    private String title;
    private List<PackageFileTreeDTO> children;
    private Boolean isLeaf;

    public static PackageFileTreeDTO toDTO(Task task) {

        return PackageFileTreeDTO.builder()
                .key(task.getId() + "")
                .title(task.getName())
                .build();
        //return null;
    }


}
