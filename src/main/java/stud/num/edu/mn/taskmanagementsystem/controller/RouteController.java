package stud.num.edu.mn.taskmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stud.num.edu.mn.taskmanagementsystem.dao.CurrentUser;
import stud.num.edu.mn.taskmanagementsystem.dao.ImsUserDAO;
import stud.num.edu.mn.taskmanagementsystem.dao.RouteDAO;
import stud.num.edu.mn.taskmanagementsystem.dao.WorkSpaceDAO;
import stud.num.edu.mn.taskmanagementsystem.entity.ImsUser;
import stud.num.edu.mn.taskmanagementsystem.entity.Route;
import stud.num.edu.mn.taskmanagementsystem.entity.work.Task;
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkPackage;
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkSpace;

import java.security.Principal;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class RouteController {

    @Autowired
    ImsUserDAO imsUserDAO;

    @Autowired
    WorkSpaceDAO workSpaceDAO;

    @Autowired
    RouteDAO routeDAO;

    @GetMapping("/routes")
    public ResponseEntity<?> partialUpdateName(Principal principal) {
        AtomicLong workSpaceCount = new AtomicLong(2L);
        AtomicLong workPackageCount = new AtomicLong(300L);
        AtomicLong taskCount = new AtomicLong(6000L);

        ImsUser currentUser = imsUserDAO.findByEmail(principal.getName());
        List<Route> list = routeDAO.findAll();

        List<WorkSpace> spaceList = workSpaceDAO.findAllByOwner(currentUser);
        spaceList.addAll(workSpaceDAO.findAllByActiveAndMember(currentUser.getId()));

        for (WorkSpace space : spaceList){
            Route route = new Route();
            route.setId(workSpaceCount.incrementAndGet());
            route.setIcon("project");
            route.setName(space.getName());
            route.setRoute("/work-space/" + space.getCode());
            for (WorkPackage workPackage: space.getWorkPackages()){
                Route subRoute = new Route();
                subRoute.setId(workPackageCount.incrementAndGet());
                subRoute.setMenuParentId(workSpaceCount.get());
                subRoute.setBreadcrumbParentId(workSpaceCount.get());
                subRoute.setName(workPackage.getName());
                subRoute.setRoute("/work-package/" + workPackage.getCode());

                for (Task task: workPackage.getTasks()){
                    Route taskRoute = new Route();
                    taskRoute.setId(taskCount.incrementAndGet());
                    taskRoute.setMenuParentId(-1L);
                    taskRoute.setName(task.getName());
                    taskRoute.setRoute("/task/" + task.getCode());
                    list.add(taskRoute);
                }
                list.add(subRoute);;
            }
            list.add(route);
        }
        // sort by id
        Collections.sort(list);
        // clear duplicates
        List<Route> result = new ArrayList<Route>();
        for(Route route: list){
            boolean discovered = false;
            for (Route item: result){
                if(item.equals(route)){
                    discovered = true;
                    break;
                }
            }
            if(!discovered){
                result.add(route);
            }
        }
        return ResponseEntity.ok(result);
    }
}
