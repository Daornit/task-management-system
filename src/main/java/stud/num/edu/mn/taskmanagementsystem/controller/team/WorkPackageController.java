package stud.num.edu.mn.taskmanagementsystem.controller.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.num.edu.mn.taskmanagementsystem.dao.*;
import stud.num.edu.mn.taskmanagementsystem.dto.CommentDTO;
import stud.num.edu.mn.taskmanagementsystem.entity.Comment;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;
import stud.num.edu.mn.taskmanagementsystem.entity.work.Task;
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkPackage;
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkSpace;
import stud.num.edu.mn.taskmanagementsystem.util.InboxService;
import stud.num.edu.mn.taskmanagementsystem.util.MentionService;
import stud.num.edu.mn.taskmanagementsystem.util.TaskCodeGenerator;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class WorkPackageController {
    @Autowired
    HrmTeamDAO hrmTeamDAO;

    @Autowired
    ImsUserDAO imsUserDAO;

    @Autowired
    WorkSpaceDAO workSpaceDAO;

    @Autowired
    UserViewDAO userViewDAO;

    @Autowired
    WorkPackageDAO workPackageDAO;

    @Autowired
    CommentDAO commentDAO;

    @Autowired
    MentionService mentionService;

    @Autowired
    InboxService inboxService;

    @GetMapping("/work-package/{code}")
    public ResponseEntity getByCode(@PathVariable("code") String code) {
        WorkPackage workPackage = workPackageDAO.findByCode(code);
        List<Task> tasks = List.copyOf(workPackage.getTasks());
        for (Task task : tasks){
            if(task.getIsDeleted()) {
                workPackage.getTasks().remove(task);
            }
        }
        return ResponseEntity.ok(workPackage);
    }

    @PostMapping("/work-package/{code}/comment")
    public ResponseEntity addComment(@PathVariable("code") String code, @RequestBody CommentDTO commentDTO) {
        WorkPackage workPackage = workPackageDAO.findByCode(code);
        System.out.println("COMMENT :: " + commentDTO.toString());
        Comment comment = new Comment();
        ImsUser owner = imsUserDAO.findById(commentDTO.getAuthor()).get();
        comment.setOwner(owner);
        comment.setCode(code);
        comment.setContent(commentDTO.getContent());
        comment.setDatetime(commentDTO.getDatetime());
        System.out.println("COMMENT :: " + comment.toString());
        Comment saved = commentDAO.save(comment);

        mentionService.mention(saved.getOwner(), saved.getContent(), "/mn/work-package/" + code);
        workPackage.getComments().add(saved);
        workPackageDAO.save(workPackage);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/work-package/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        WorkPackage workPackage = workPackageDAO.findById(id).get();
        workPackage.setIsDeleted(true);
        workPackageDAO.save(workPackage);
        return ResponseEntity.ok("Амжилттай устгалаа!");
    }

    @PutMapping("/work-package/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody WorkPackage pack, Principal principal) {
        ImsUser owner = imsUserDAO.findByEmail(principal.getName());
        WorkPackage workPackage = workPackageDAO.findById(id).get();
        workPackage.setWorkSpaceCode(pack.getWorkSpaceCode());
        workPackage.setName(pack.getName());
        workPackage.setContent(pack.getContent());
        workPackage.setDefaultView(pack.getDefaultView());
        if(workPackage.getOwnerId() != null) owner = imsUserDAO.findById(workPackage.getOwnerId()).get();
        workPackage.setOwner(owner);
        workPackageDAO.save(workPackage);
        return ResponseEntity.ok("Амжилттай шинжиллээ!");
    }
    @PostMapping("/work-package")
    public ResponseEntity create(@RequestBody() WorkPackage workPackage, Principal principal) {
        ImsUser owner = imsUserDAO.findByEmail(principal.getName());

        System.out.println("workPackage :: " + workPackage.toString());
        String workPackageCode = TaskCodeGenerator.newCode();

        workPackage.setCode(workPackageCode);
        if(workPackage.getOwnerId() != null) owner = imsUserDAO.findById(workPackage.getOwnerId()).get();
        workPackage.setOwner(owner);
        workPackage.setContent("Энэхүү хэсэгт төслийн тайлбарыг бичнэ үү");
        workPackage.setIsDeleted(false);
        WorkPackage saved = workPackageDAO.save(workPackage);

        WorkSpace workSpace = workSpaceDAO.findByCode(workPackage.getWorkSpaceCode());
        if(workSpace != null) {
            workSpace.getWorkPackages().add(saved);
        }
        workSpaceDAO.save(workSpace);

        return ResponseEntity.ok("Ажлын багц амжилттай үүсгэлээ.");
    }
}
