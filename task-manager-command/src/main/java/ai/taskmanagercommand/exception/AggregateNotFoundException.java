package ai.taskmanagercommand.exception;

import java.text.MessageFormat;
import java.util.UUID;

public class AggregateNotFoundException extends RuntimeException {
    public AggregateNotFoundException(UUID aggregateId) {
        super(MessageFormat.format("Агрегат с id={0} не найден", aggregateId));
    }
}
