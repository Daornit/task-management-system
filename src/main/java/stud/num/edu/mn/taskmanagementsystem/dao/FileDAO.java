package stud.num.edu.mn.taskmanagementsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.num.edu.mn.taskmanagementsystem.entity.DbFile;

import java.util.List;
/*
@author Bat-orgil
@date 2019-12-01
*/
public interface FileDAO extends JpaRepository<DbFile, String> {
    List<DbFile> findAllByCode(String code);
}
