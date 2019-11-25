package stud.num.edu.mn.taskmanagementsystem.controller.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stud.num.edu.mn.taskmanagementsystem.dao.*;
import stud.num.edu.mn.taskmanagementsystem.dto.IdModel;
import stud.num.edu.mn.taskmanagementsystem.dto.WorkSpaceCreateDTO;
import stud.num.edu.mn.taskmanagementsystem.dto.WorkSpaceDTO;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;
import stud.num.edu.mn.taskmanagementsystem.entity.Inbox;
import stud.num.edu.mn.taskmanagementsystem.entity.UserView;
import stud.num.edu.mn.taskmanagementsystem.entity.Team;
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkPackage;
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkSpace;
import stud.num.edu.mn.taskmanagementsystem.util.InboxService;
import stud.num.edu.mn.taskmanagementsystem.util.TaskCodeGenerator;

import java.lang.reflect.Method;
import java.security.Principal;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class WorkSpaceController {
    @Autowired
    UserViewDAO userViewDAO;

    @Autowired
    HrmTeamDAO hrmTeamDAO;

    @Autowired
    ImsUserDAO imsUserDAO;

    @Autowired
    WorkSpaceDAO workSpaceDAO;

    @Autowired
    InboxService inboxService;

    @GetMapping("/work-space/{code}")
    public ResponseEntity get(@PathVariable("code") String code) {
        WorkSpace workSpace = workSpaceDAO.findByCode(code);
        List<WorkPackage> list = List.copyOf(workSpace.getWorkPackages());
        for (WorkPackage workPackage : list){
            if(workPackage.getIsDeleted()) {
                workSpace.getWorkPackages().remove(workPackage);
            }
        }
        return ResponseEntity.ok(workSpace);
    }

    @GetMapping("/work-space")
    public ResponseEntity get() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class cls = Class.forName("stud.num.edu.mn.taskmanagementsystem.entity.ImsUser");
        Object obj = cls.newInstance();
        Method[] methods =  cls.getDeclaredMethods();
        for( int i = 0 ; i < methods.length ; i++ )
        {
            if(methods[i].getName().equals("getPassword")) methods[i].setAccessible(false);
        }
        return ResponseEntity.ok(workSpaceDAO.findAll());
    }

    @GetMapping("/work-space/{code}/users")
    public ResponseEntity getUsers(@PathVariable("code") String code) {
        WorkSpace workSpace = workSpaceDAO.findByCode(code);
        return ResponseEntity.ok(workSpace.getTeam().getMembers());
    }

    @DeleteMapping("/work-space/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        WorkSpace workSpace = workSpaceDAO.findById(id).get();
        workSpace.setIsDeleted(true);
        workSpaceDAO.save(workSpace);
        return ResponseEntity.ok("Амжилттай устгалаа!");
    }

    @PutMapping("/work-space/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody WorkSpaceDTO space) {
        WorkSpace workSpace = workSpaceDAO.findById(id).get();
        workSpace.setName(space.getName());
        if(workSpace.getTeam() != null) {
            Team team = hrmTeamDAO.findById(workSpace.getTeam().getId()).get();
            team.getMembers().clear();
            for(IdModel user: space.getTeam().getMembers()){
                ImsUser u = imsUserDAO.findById(user.getId()).get();
                if(u != null) {
                    team.addUser(u);
                    System.out.println("USER :: " + u.toString());
                }

            }
            hrmTeamDAO.save(team);
        }
        workSpaceDAO.save(workSpace);
        return ResponseEntity.ok("Амжилттай шинжиллээ!");
    }
    @PostMapping("/work-space")
    public ResponseEntity create(@RequestBody() WorkSpaceCreateDTO workSpaceModel, Principal principal) {
        ImsUser currentUser = imsUserDAO.findByEmail(principal.getName());

        System.out.println("workSpaceDTO :: " + workSpaceModel.toString());
        String teamCode = TaskCodeGenerator.newCode();
        String workSpaceCode = TaskCodeGenerator.newCode();

        WorkSpace workSpace = new WorkSpace();
        workSpace.setName(workSpaceModel.getName());
        workSpace.setCode(workSpaceCode);
        workSpace.setIsDeleted(false);
        workSpace.setWorkPackages(null);
        workSpace.setOwner(currentUser);

        List<Long> ids = workSpaceModel.getMembers().stream().map(x -> x.getId()).collect(Collectors.toList());
        List<ImsUser> users = imsUserDAO.findAllByIdIn(ids);

        Team team = new Team();
        team.setCode(teamCode);
        team.setWorkSpaceCode(workSpaceCode);
        for (ImsUser user: users) {
            team.addUser(user);
            inboxService.addInbox(
                    "Таныг ажлын орчинд нэмсэн байна.",
                    "Таныг " + workSpace.getName() + " энэхүү ажлын орчин гишүүнээр нэмсэн байна.",
                    user,
                    "/mn/work-space/" + workSpace.getCode()
            );
        }

        inboxService.addInbox(
                "Ажлын орчин томилолт.",
                "Таныг " + workSpace.getName() + " энэхүү ажлын орчинд удирдагчаар томилсон байна.",
                currentUser,
                "/mn/work-space/" + workSpace.getCode()
        );
        workSpace.setTeam(team);
        hrmTeamDAO.save(team);
        workSpaceDAO.save(workSpace);
        return ResponseEntity.ok("Ажлын орчин амжилттай үүсгэлээ.");
    }
}
