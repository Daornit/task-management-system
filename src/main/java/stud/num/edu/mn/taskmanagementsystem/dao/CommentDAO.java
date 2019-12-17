package stud.num.edu.mn.taskmanagementsystem.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import stud.num.edu.mn.taskmanagementsystem.entity.Comment;

public interface CommentDAO extends PagingAndSortingRepository<Comment, Long> {
}
