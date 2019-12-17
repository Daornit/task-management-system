package stud.num.edu.mn.taskmanagementsystem.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import stud.num.edu.mn.taskmanagementsystem.entity.Comment;
/*
@author Bat-orgil
@date 2019-12-01
*/
public interface CommentDAO extends PagingAndSortingRepository<Comment, Long> {
}
