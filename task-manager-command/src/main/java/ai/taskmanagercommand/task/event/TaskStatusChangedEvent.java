package ai.taskmanagercommand.task.event;

import ai.taskmanagercommand.task.domain.TaskStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

/**
 * Event изменения статуса таски
 */
@EqualsAndHashCode(callSuper = true)
@Getter
public class TaskStatusChangedEvent extends TaskBaseEvent {
    /**
     * Тип event
     */
    public static final String EVENT_TYPE = "TASK_STATUS_CHANGED";
    /**
     * ID статуса
     */
    private final TaskStatus taskStatus;

    @Builder(setterPrefix = "with")
    public TaskStatusChangedEvent(UUID aggregateId, TaskStatus taskStatus) {
        super(aggregateId);
        this.taskStatus = taskStatus;
    }
}