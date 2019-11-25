package stud.num.edu.mn.taskmanagementsystem.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import stud.num.edu.mn.taskmanagementsystem.entity.Route;

import java.util.List;

public interface RouteDAO extends PagingAndSortingRepository<Route, Long> {
    @Query("SELECT s FROM Route s ORDER BY s.id ASC")
    List<Route> findAll();
}
