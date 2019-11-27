package stud.num.edu.mn.taskmanagementsystem.controller.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.num.edu.mn.taskmanagementsystem.dao.*;
import stud.num.edu.mn.taskmanagementsystem.dto.CommentDTO;
import stud.num.edu.mn.taskmanagementsystem.dto.TaskPersonalDTO;
import stud.num.edu.mn.taskmanagementsystem.entity.Comment;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;
import stud.num.edu.mn.taskmanagementsystem.entity.work.Task;
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkPackage;
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkSpace;
import stud.num.edu.mn.taskmanagementsystem.util.InboxService;
import stud.num.edu.mn.taskmanagementsystem.util.MentionService;
import stud.num.edu.mn.taskmanagementsystem.util.TaskCodeGenerator;

import java.security.Principal;
import java.util.List;
//Хүсэлтийг тодорхойлж удирдах хэсэг
@RestController
//Хүсэлтийг api гаар буцааж буй төрлүүд
@RequestMapping("/api/v1")
//Системийн үндсэн модулуудад харьяалагдах утгуудыг тодорхойлох
public class TaskController {
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
    TaskDAO taskDAO;

    @Autowired
    InboxService inboxService;
    
    //GET ээр утгаа дамжуулах
    @GetMapping("/task/{code}")
    public ResponseEntity getByCode(@PathVariable("code") String code) {
        Task task = taskDAO.findByCode(code);
        List<Task> subTasks = List.copyOf(task.getTasks());
        for (Task tsk : subTasks){
            System.out.println("ParentId :: " + tsk.getParentId());
            if(tsk.getIsDeleted() || tsk.getParentId() == null) {
                tsk.getTasks().remove(tsk);
            }
        }
        return ResponseEntity.ok(task);
    }

    //POST оор даалгавар үүсгэхэд оруулах утгыг серверлүү дамжуулах
    @PostMapping("/task/{code}/comment")
    public ResponseEntity addComment(@PathVariable("code") String code, @RequestBody CommentDTO commentDTO) {
        Task task = taskDAO.findByCode(code);
        Comment comment = new Comment();
        ImsUser owner = imsUserDAO.findById(commentDTO.getAuthor()).get();
        comment.setOwner(owner);
        comment.setCode(code);
        comment.setContent(commentDTO.getContent());
        comment.setDatetime(commentDTO.getDatetime());
        System.out.println("COMMENT :: " + comment.toString());
        Comment saved = commentDAO.save(comment);

        mentionService.mention(saved.getOwner(), saved.getContent(), "/mn/task/" + code);
        task.getComments().add(saved);
        taskDAO.save(task);
        return ResponseEntity.ok(saved);
    }

    //DELETE ээр устгах хэсгийг id гаар нь олж устгана
    @DeleteMapping("/task/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Task task = taskDAO.findById(id).get();
        task.setIsDeleted(true);
        taskDAO.save(task);
        return ResponseEntity.ok("Амжилттай устгалаа!");
    }

    //PUT ээр даалгавар засах ба хэрэглэгчид даалгавар томилж өгж байна
    @PutMapping("/task/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Task receivedTask, Principal principal) {

        System.out.println("receivedTask :: " + receivedTask.toString());
        ImsUser owner = imsUserDAO.findByEmail(principal.getName());
        Task task = taskDAO.findById(id).get();
        task.setWorkPackageCode(receivedTask.getWorkPackageCode());
        task.setName(receivedTask.getName());
        task.setContent(receivedTask.getContent());
        task.setProcess(receivedTask.getProcess());
        task.setEndDate(receivedTask.getEndDate());
        task.setStatus(receivedTask.getStatus());
        task.setStartDate(receivedTask.getStartDate());
        if(task.getOwnerId() != null) owner = imsUserDAO.findById(receivedTask.getOwnerId()).get();
        task.setOwner(owner);
        if(receivedTask.getAssignId() != null) {
            ImsUser assign = imsUserDAO.findById(receivedTask.getAssignId()).get();
            task.setAssign(assign);
        };

        System.out.println("saved :: " + task.toString());
        Task saved = taskDAO.save(task);

        if(saved.getAssign() != null) {
            inboxService.addInbox(
                    "Танд даалгавар хуваарьлсан байна.",
                    "Таныг " + saved.getAssign().getUsername() + " хэрэглэгч \"" + task.getName() + "\" нэртэй даалгавар дээр томилсон байна.",
                    saved.getAssign(),
                    "/mn/task/" + saved.getCode()
            );
        }
        return ResponseEntity.ok("Амжилттай шинжиллээ!");
    }
    //Шинэ үүсгэсэн даалгаврын default утга болон хэрхэн хэнд томилох
    @PostMapping("/task")
    public ResponseEntity create(@RequestBody() Task task, Principal principal) {
        ImsUser owner = imsUserDAO.findByEmail(principal.getName());

        System.out.println("task :: " + task.toString());
        String taskCode = TaskCodeGenerator.newCode();

        task.setCode(taskCode);
        if(task.getOwnerId() != null) owner = imsUserDAO.findById(task.getOwnerId()).get();
        task.setOwner(owner);
        task.setContent("Энэхүү хэсэгт төслийн тайлбарыг бичнэ үү");
        task.setIsDeleted(false);


        if(task.getAssignId() != null) {
            ImsUser assign = imsUserDAO.findById(task.getAssignId()).get();
            task.setAssign(assign);
        };

        Task saved = taskDAO.save(task);

        WorkPackage workPackage = workPackageDAO.findByCode(task.getWorkPackageCode());
        if(workPackage != null) {
            workPackage.getTasks().add(saved);
        }
        workPackageDAO.save(workPackage);
        return ResponseEntity.ok(workPackage);
    }

    //Тухайн хүнд томилсон даалгавар
    @GetMapping("/to-do")
    public ResponseEntity toDo(Principal principal) {
        ImsUser currentUser = imsUserDAO.findByEmail(principal.getName());
        List<Task> tasks = taskDAO.findAllByAssignAndIsDeleted(currentUser, false);
        TaskPersonalDTO taskPersonalDTO = new TaskPersonalDTO();
        taskPersonalDTO.setTasks(tasks);
        return ResponseEntity.ok(taskPersonalDTO);
    }

    //тухайн хүний үүсгэсэн даалгавар
    @GetMapping("/to-assign")
    public ResponseEntity toAssign(Principal principal) {
        ImsUser currentUser = imsUserDAO.findByEmail(principal.getName());
        List<Task> tasks = taskDAO.findAllByOwnerAndIsDeleted(currentUser, false);
        TaskPersonalDTO taskPersonalDTO = new TaskPersonalDTO();
        taskPersonalDTO.setTasks(tasks);
        return ResponseEntity.ok(taskPersonalDTO);
    }
}
