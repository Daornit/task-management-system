package stud.num.edu.mn.taskmanagementsystem;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import stud.num.edu.mn.taskmanagementsystem.controller.FileController;
import stud.num.edu.mn.taskmanagementsystem.controller.InboxController;
import stud.num.edu.mn.taskmanagementsystem.controller.PublicController;
import stud.num.edu.mn.taskmanagementsystem.controller.RouteController;
import stud.num.edu.mn.taskmanagementsystem.controller.team.TaskController;
import stud.num.edu.mn.taskmanagementsystem.controller.team.TeamController;
import stud.num.edu.mn.taskmanagementsystem.controller.team.WorkPackageController;
import stud.num.edu.mn.taskmanagementsystem.controller.team.WorkSpaceController;
import stud.num.edu.mn.taskmanagementsystem.entity.work.WorkPackage;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {

    @Autowired
    private TaskController taskController;
    @Autowired
    private TeamController teamController;
    @Autowired
    private WorkPackageController workPackageController;
    @Autowired
    private WorkSpaceController workSpaceController;
    @Autowired
    private FileController fileController;
    @Autowired
    private InboxController inboxController;
    @Autowired
    private PublicController publicController;
    @Autowired
    private RouteController routeController;


    @Test
    public void contexLoads() throws Exception {
        assertThat(taskController).isNotNull();
        assertThat(teamController).isNotNull();
        assertThat(workPackageController).isNotNull();
        assertThat(workSpaceController).isNotNull();
        assertThat(fileController).isNotNull();
        assertThat(inboxController).isNotNull();
        assertThat(publicController).isNotNull();
        assertThat(routeController).isNotNull();
    }
}
