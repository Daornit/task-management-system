package stud.num.edu.mn.taskmanagementsystem.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import stud.num.edu.mn.taskmanagementsystem.entity.Team;

public interface HrmTeamDAO extends PagingAndSortingRepository<Team, Long> {
}
