package stud.num.edu.mn.taskmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stud.num.edu.mn.taskmanagementsystem.dao.ImsUserDAO;
import stud.num.edu.mn.taskmanagementsystem.dao.InboxDAO;
import stud.num.edu.mn.taskmanagementsystem.dao.RouteDAO;
import stud.num.edu.mn.taskmanagementsystem.dao.WorkSpaceDAO;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;
import stud.num.edu.mn.taskmanagementsystem.entity.Inbox;
import stud.num.edu.mn.taskmanagementsystem.entity.Route;
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkPackage;
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkSpace;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InboxController {

    @Autowired
    ImsUserDAO imsUserDAO;

    @Autowired
    InboxDAO inboxDAO;

    @GetMapping("/notifications")
    public ResponseEntity<?> getAllNotification(Principal principal) {
        ImsUser currentUser = imsUserDAO.findByEmail(principal.getName());
        List<Inbox> inboxes = inboxDAO.findAllByUser(currentUser);
        List<Inbox> notifications = new ArrayList<>();
        for (Inbox inbox: inboxes){
            if(inbox.getNotified() == null || !inbox.getNotified()) notifications.add(inbox);
        }
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/inbox")
    public ResponseEntity<?> getAllInbox(Principal principal) {
        ImsUser currentUser = imsUserDAO.findByEmail(principal.getName());
        return ResponseEntity.ok(inboxDAO.findAllByUser(currentUser));
    }

    @GetMapping("/inbox/{code}")
    public ResponseEntity getByCode(@PathVariable String code){
        Inbox inbox = inboxDAO.findByCode(code);
        inbox.setNotified(true);
        inboxDAO.save(inbox);
        return ResponseEntity.ok(inbox);
    }
}
