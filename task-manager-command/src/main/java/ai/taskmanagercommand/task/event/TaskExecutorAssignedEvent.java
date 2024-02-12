package ai.taskmanagercommand.task.event;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

/**
 * Event добавление исполнителя к таске
 */
@EqualsAndHashCode(callSuper = true)
@Getter
public class TaskExecutorAssignedEvent extends TaskBaseEvent {
    /**
     * Тип event
     */
    public static final String EVENT_TYPE = "TASK_EXECUTOR_ASSIGNED";
    /**
     * ID пользователя
     */
    private final UUID userId;

    @Builder(setterPrefix = "with")
    public TaskExecutorAssignedEvent(UUID aggregateId, UUID userId) {
        super(aggregateId);
        this.userId = userId;
    }
}
