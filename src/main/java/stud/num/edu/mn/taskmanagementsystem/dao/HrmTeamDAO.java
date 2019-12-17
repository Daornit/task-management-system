package stud.num.edu.mn.taskmanagementsystem.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import stud.num.edu.mn.taskmanagementsystem.entity.Team;
/*
@author Bat-orgil
@date 2019-12-01
*/
public interface HrmTeamDAO extends PagingAndSortingRepository<Team, Long> {
}
