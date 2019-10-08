package stud.num.edu.mn.taskmanagementsystem.controller.activiti;

import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.ProcessInstanceMeta;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activiti")
public class ActivitiController {

    private Logger logger = LoggerFactory.getLogger(ActivitiController.class);

    @Autowired
    private ProcessRuntime processRuntime;

    @GetMapping("/process-definitions")
    public List<ProcessDefinition> getProcessDefinitions() {
        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        logger.info("> Available Process definitions: " + processDefinitionPage.getTotalItems());

        for (ProcessDefinition pd : processDefinitionPage.getContent()) {
            logger.info("\t > Process definition: " + pd);
        }

        return processDefinitionPage.getContent();
    }

    @RequestMapping("/start-process")
    public ProcessInstance startProcess(
            @RequestParam(value="processDefinitionKey", defaultValue="sample-diagram") String processDefinitionKey) {
        ProcessInstance processInstance = processRuntime.start(ProcessPayloadBuilder
                .start()
                .withProcessDefinitionKey(processDefinitionKey)
                .withVariable("someProcessVar", "someProcVarValue")
                .build());
        logger.info(">>> Created Process Instance: " + processInstance);

        return processInstance;
    }

    @GetMapping("/process-instances")
    public List<ProcessInstance> getProcessInstances() {
        List<ProcessInstance> processInstances =
                processRuntime.processInstances(Pageable.of(0, 10)).getContent();

        return processInstances;
    }

    @GetMapping("/process-instance-meta")
    public ProcessInstanceMeta getProcessInstanceMeta(@RequestParam(value="processInstanceId") String processInstanceId) {
        ProcessInstanceMeta processInstanceMeta = processRuntime.processInstanceMeta(processInstanceId);

        return processInstanceMeta;
    }

    @GetMapping("/test")
    public ResponseEntity test() {
        return ResponseEntity.ok("TEST");
    }
}
