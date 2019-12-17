package stud.num.edu.mn.taskmanagementsystem.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;
import stud.num.edu.mn.taskmanagementsystem.entity.work.Task;

import java.util.List;

//Өгөгдлийн сантай холболт үүсгэн query хийх боломж олгоно.
public interface TaskDAO extends PagingAndSortingRepository<Task, Long> {
    Task findByCode(String code);

    List<Task> findAllByOwnerAndIsDeleted(ImsUser user, Boolean deleted);

    List<Task> findAllByAssignAndIsDeleted(ImsUser user, Boolean deleted);
}
