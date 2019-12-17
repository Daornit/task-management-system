package stud.num.edu.mn.taskmanagementsystem.dao;

import org.springframework.data.repository.CrudRepository;
import stud.num.edu.mn.taskmanagementsystem.entity.ConfirmationToken;
/*
@author Bat-orgil
@date 2019-12-01
*/
public interface ConfirmationTokenDAO extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationTokenAndActive(String confirmationToken, Boolean active);
}
