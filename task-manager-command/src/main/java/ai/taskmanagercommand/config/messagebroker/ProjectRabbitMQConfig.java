package ai.taskmanagercommand.config.messagebroker;

import ai.taskmanagercommand.properties.ProjectQueueProperties;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectRabbitMQConfig extends AbstractRabbitMQBindingConfig {

    @Autowired
    @Override
    public void declare(RabbitAdmin rabbitAdmin) {
        binding(rabbitAdmin,
                ProjectQueueProperties.ROUTE_KEYS.values(),
                ProjectQueueProperties.EXCHANGE,
                ProjectQueueProperties.QUEUE
        );
    }
}
