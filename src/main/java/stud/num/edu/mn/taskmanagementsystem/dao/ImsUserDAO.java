package stud.num.edu.mn.taskmanagementsystem.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;

@RepositoryRestResource(collectionResourceRel = "imsUser", path = "imsUser")
public interface ImsUserDAO extends PagingAndSortingRepository<ImsUser, Long> {
    @Query("SELECT s from ImsUser s where s.email = ?1")
    ImsUser findByEmail(String email);
}
