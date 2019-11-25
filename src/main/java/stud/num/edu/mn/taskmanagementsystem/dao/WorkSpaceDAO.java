package stud.num.edu.mn.taskmanagementsystem.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkSpace;

import java.util.List;

public interface WorkSpaceDAO extends PagingAndSortingRepository<WorkSpace, Long> {
    @Query("SELECT w FROM WorkSpace w " +
            "INNER JOIN w.team t " +
            "INNER JOIN t.members m " +
            "WHERE w.isDeleted = false AND m.id = ?1")
    List<WorkSpace> findAllByActiveAndMember(Long id);

    @Query("SELECT w FROM WorkSpace w WHERE w.owner = ?1 and w.isDeleted = false")
    List<WorkSpace> findAllByOwner(ImsUser user);

    WorkSpace findByCode(String code);
}
