package stud.num.edu.mn.taskmanagementsystem.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsRole;

@RepositoryRestResource(collectionResourceRel = "imsRole", path = "imsRole")
public interface ImsRoleDAO extends PagingAndSortingRepository<ImsRole, Long> {
}
