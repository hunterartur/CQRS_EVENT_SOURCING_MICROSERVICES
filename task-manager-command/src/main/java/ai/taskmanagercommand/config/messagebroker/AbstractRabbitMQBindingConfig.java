package ai.taskmanagercommand.config.messagebroker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

import java.util.Collection;

@Slf4j
public abstract class AbstractRabbitMQBindingConfig {
    public abstract void declare(RabbitAdmin rabbitAdmin);

    protected void binding(RabbitAdmin rabbitAdmin,
                           Collection<String> routeKeys,
                           String exchangeName,
                           String projectQueueName) {
        var exchange = new DirectExchange(exchangeName, true, false);
        var queue = new Queue(projectQueueName);
        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareQueue(queue);
        for (var routeKey : routeKeys) {
            var binding = new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), routeKey, null);
            rabbitAdmin.declareBinding(binding);
            log.info("Created bindings: {} -> ({}) -> {}", exchange.getName(), routeKey, queue.getName());
        }
    }
}
