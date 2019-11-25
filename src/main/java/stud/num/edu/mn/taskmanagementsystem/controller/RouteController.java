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
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkPackage;
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkSpace;

import java.security.Principal;
import java.util.*;
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
        ImsUser currentUser = imsUserDAO.findByEmail(principal.getName());
        List<Route> list = routeDAO.findAll();

        List<WorkSpace> spaceList = workSpaceDAO.findAllByOwner(currentUser);
        spaceList.addAll(workSpaceDAO.findAllByActiveAndMember(currentUser.getId()));

        Long i = 2L;
        for (WorkSpace space : spaceList){
            Route route = new Route();
            route.setId(i);
            route.setIcon("project");
            route.setName(space.getName());
            route.setRoute("/work-space/" + space.getCode());
            Long j = 300L;
            for (WorkPackage workPackage: space.getWorkPackages()){
                Route subRoute = new Route();
                subRoute.setId(j);
                subRoute.setMenuParentId(i);
                subRoute.setBreadcrumbParentId(i);
                subRoute.setName(workPackage.getName());
                subRoute.setRoute("/work-package/" + workPackage.getCode());
                list.add(subRoute);
                j = j + 1L;
            }
            list.add(route);
            i = i + 1L;
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
