package stud.num.edu.mn.taskmanagementsystem.service.activiti;

import org.springframework.stereotype.Service;
import org.activiti.api.process.model.IntegrationContext;
import org.activiti.api.process.runtime.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

@Service(value = "serviceTask1Impl")
public class ServiceTask1Connector implements Connector {
    private Logger logger = LoggerFactory.getLogger(ServiceTask1Connector.class);

    @Override
    public IntegrationContext apply(IntegrationContext integrationContext) {
        logger.info("Some service task logic... [processInstanceId=" + integrationContext.getProcessInstanceId() + "]");
        return integrationContext;
    }

    @Override
    public <V> Function<V, IntegrationContext> compose(Function<? super V, ? extends IntegrationContext> before) {
        return null;
    }

    @Override
    public <V> Function<IntegrationContext, V> andThen(Function<? super IntegrationContext, ? extends V> after) {
        return null;
    }
}
