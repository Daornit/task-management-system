package stud.num.edu.mn.taskmanagementsystem.listener.activiti;


import org.activiti.api.model.shared.event.RuntimeEvent;
import org.activiti.api.model.shared.event.VariableCreatedEvent;
import org.activiti.api.process.model.events.SequenceFlowEvent;
import org.activiti.api.process.runtime.events.*;
import org.activiti.api.process.runtime.events.listener.ProcessRuntimeEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProcessEventListener implements ProcessRuntimeEventListener{

    private Logger logger = LoggerFactory.getLogger(ProcessEventListener.class);

    @Override
    public void onEvent(RuntimeEvent runtimeEvent) {
        if (runtimeEvent instanceof ProcessStartedEvent)
            logger.info("Do something, process is started: " + runtimeEvent.toString());
        else if (runtimeEvent instanceof ProcessCompletedEvent)
            logger.info("Do something, process is completed: " + runtimeEvent.toString());
        else if (runtimeEvent instanceof ProcessCancelledEvent)
            logger.info("Do something, process is cancelled: " + runtimeEvent.toString());
        else if (runtimeEvent instanceof ProcessSuspendedEvent)
            logger.info("Do something, process is suspended: " + runtimeEvent.toString());
        else if (runtimeEvent instanceof ProcessResumedEvent)
            logger.info("Do something, process is resumed: " + runtimeEvent.toString());
        else if (runtimeEvent instanceof ProcessCreatedEvent)
            logger.info("Do something, process is created: " + runtimeEvent.toString());
        else if (runtimeEvent instanceof SequenceFlowEvent)
            logger.info("Do something, sequence flow is taken: " + runtimeEvent.toString());
        else if (runtimeEvent instanceof VariableCreatedEvent)
            logger.info("Do something, variable was created: " + runtimeEvent.toString());
        else
            logger.info("Unknown event: " + runtimeEvent.toString());
    }
}
