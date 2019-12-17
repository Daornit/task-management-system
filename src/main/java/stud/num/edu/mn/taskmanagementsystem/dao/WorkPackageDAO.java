package stud.num.edu.mn.taskmanagementsystem.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkPackage;
/*
@author Bat-orgil
@date 2019-12-01
*/
public interface WorkPackageDAO extends PagingAndSortingRepository<WorkPackage, Long> {
    WorkPackage findByCode(String code);
}
