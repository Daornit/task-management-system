package stud.num.edu.mn.taskmanagementsystem.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import stud.num.edu.mn.taskmanagementsystem.entity.Comment;

import java.util.List;

public interface CommentDAO extends PagingAndSortingRepository<Comment, Long> {
}
