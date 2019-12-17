package stud.num.edu.mn.taskmanagementsystem.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkPackage;

public interface WorkPackageDAO extends PagingAndSortingRepository<WorkPackage, Long> {
    WorkPackage findByCode(String code);
}
