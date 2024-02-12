package ai.taskmanagercommand.config.messagebroker;

import ai.taskmanagercommand.properties.TaskQueueProperties;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskRabbitMQConfig extends AbstractRabbitMQBindingConfig {

    @Autowired
    @Override
    public void declare(RabbitAdmin rabbitAdmin) {
        binding(rabbitAdmin,
                TaskQueueProperties.ROUTE_KEYS.values(),
                TaskQueueProperties.EXCHANGE,
                TaskQueueProperties.QUEUE
        );
    }
}
