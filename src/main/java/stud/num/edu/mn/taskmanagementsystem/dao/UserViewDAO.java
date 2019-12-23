package stud.num.edu.mn.taskmanagementsystem.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import stud.num.edu.mn.taskmanagementsystem.entity.UserView;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "userView", path = "userView")
public interface UserViewDAO extends PagingAndSortingRepository<UserView, Long> {
    @Query("SELECT s FROM UserView s WHERE s.email like CONCAT('%',:email,'%') AND s.groupCode = :groupCode")
    Page<UserView> findAllByEmail(String email, String groupCode, Pageable pageable);

    Page<UserView> findAllByGroupCode(String groupCode, Pageable pageable);
    UserView findByEmail(String email);

    List<UserView> findAllByGroupCodeAndEnabled(String groupCode, Boolean enabled);
}
