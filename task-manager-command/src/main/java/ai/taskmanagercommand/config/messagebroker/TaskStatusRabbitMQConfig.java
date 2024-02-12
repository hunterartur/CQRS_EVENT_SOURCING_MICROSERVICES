package ai.taskmanagercommand.config.messagebroker;

import ai.taskmanagercommand.properties.TaskStatusQueueProperties;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskStatusRabbitMQConfig extends AbstractRabbitMQBindingConfig {

    @Autowired
    @Override
    public void declare(RabbitAdmin rabbitAdmin) {
        binding(rabbitAdmin,
                TaskStatusQueueProperties.ROUTE_KEYS.values(),
                TaskStatusQueueProperties.EXCHANGE,
                TaskStatusQueueProperties.QUEUE
        );
    }
}
