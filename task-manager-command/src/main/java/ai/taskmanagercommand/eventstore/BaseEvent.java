package ai.taskmanagercommand.eventstore;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

/**
 * Парент для всех событии создаваемых агрегатами
 */
@Getter
@EqualsAndHashCode
public abstract class BaseEvent {
    /**
     * Идентификатор агрегата
     */
    protected UUID aggregateId;

    public BaseEvent(@NotNull(message = "ID агрегата не должен быть NULL") UUID aggregateId) {
        this.aggregateId = aggregateId;
    }
}
