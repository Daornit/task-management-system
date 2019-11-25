package stud.num.edu.mn.taskmanagementsystem.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;
import stud.num.edu.mn.taskmanagementsystem.entity.Inbox;

import java.util.List;

public interface InboxDAO extends PagingAndSortingRepository<Inbox, Long> {
    @Query("SELECT i FROM Inbox i WHERE i.user = ?1 order by  i.date desc")
    List<Inbox> findAllByUser(ImsUser user);
    Inbox findByCode(String code);
}
