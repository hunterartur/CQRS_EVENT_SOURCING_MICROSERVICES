package ai.taskmanagercommand.eventstore;

import ai.taskmanagercommand.common.RouteKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitEventBus implements EventBus {

    private final RabbitTemplate rabbitTemplate;

    private final static long SEND_TIMEOUT = 3000;

    @Override
    public void publish(ArrayList<Event> events, String exchange, RouteKey routeKey) {
        final var converter = new Jackson2JsonMessageConverter();
        final var message = converter.toMessage(events, new MessageProperties());
        rabbitTemplate.convertAndSend(exchange, routeKey.getName(), message);
    }
}
