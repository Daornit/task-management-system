package stud.num.edu.mn.taskmanagementsystem.controller.team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.num.edu.mn.taskmanagementsystem.controller.activiti.ActivitiController;
import stud.num.edu.mn.taskmanagementsystem.dao.ImsRoleDAO;
import stud.num.edu.mn.taskmanagementsystem.dao.ImsUserDAO;
import stud.num.edu.mn.taskmanagementsystem.dao.UserViewDAO;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;
import stud.num.edu.mn.taskmanagementsystem.entity.UserView;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/team")
public class TeamController {
    private Logger log = LoggerFactory.getLogger(ActivitiController.class);

    @Autowired
    UserViewDAO userViewDAO;

    @Autowired
    ImsUserDAO imsUserDAO;

    @Autowired
    ImsRoleDAO imsRoleDAO;

    @RequestMapping(value="/users", method= {RequestMethod.GET})
    public ResponseEntity userList(Pageable pageable, @RequestParam(value = "email", required=false) String email, Principal principal)
    {
        System.out.println("principal:: " + principal.getName());
        UserView currentUser = userViewDAO.findByEmail(principal.getName());

        Pageable dec = PageRequest.of(0, 10);
        if(pageable.hasPrevious()){
            dec = pageable.previousOrFirst();
        }
        if(email!=null) return ResponseEntity.ok(userViewDAO.findAllByEmail(email, currentUser.getGroupCode(), dec));
        return ResponseEntity.ok(userViewDAO.findAllByGroupCode(currentUser.getGroupCode(), dec));
    }

    @RequestMapping(value="/users/list", method= {RequestMethod.GET})
    public ResponseEntity userList(Principal principal)
    {
        UserView currentUser = userViewDAO.findByEmail(principal.getName());
        return ResponseEntity.ok(userViewDAO.findAllByGroupCodeAndEnabled(currentUser.getGroupCode(), true));
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<?> partialUpdateName(@RequestBody UserView userView, @PathVariable("id") Long id) {
        ImsUser user = imsUserDAO.findById(id).get();
        user.setFirstName(userView.getFirstName());
        user.setLastName(userView.getLastName());
        user.setAvatar(userView.getAvatar());
        user.setPhone(userView.getPhone());
        user.setEmail(userView.getEmail());
        user.setRole(imsRoleDAO.findByRole(userView.getRole()));
        imsUserDAO.save(user);
        return ResponseEntity.ok("resource address updated");
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        ImsUser user = imsUserDAO.findById(id).get();
        user.setEnabled(false);
        imsUserDAO.save(user);
        return ResponseEntity.ok("resource address deleted");
    }
}