package stud.num.edu.mn.taskmanagementsystem.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;

@RepositoryRestResource(collectionResourceRel = "imsUser", path = "imsUser")
public interface ImsUserDAO extends PagingAndSortingRepository<ImsUser, Long> {

    @Query("SELECT u FROM ImsUser u LEFT JOIN FETCH u.roles rl WHERE u.username=?1 AND rl.isActive=true")
    ImsUser findByUsername(String username);
}
