package ai.taskmanagercommand.task.event;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

/**
 * Event изменения имени таски
 */
@EqualsAndHashCode(callSuper = true)
@Getter
public class TaskNameChangedEvent extends TaskBaseEvent {
    /**
     * Тип event
     */
    public static final String EVENT_TYPE = "TASK_NAME_CHANGED";
    /**
     * Новое имя
     */
    private final String newName;

    @Builder(setterPrefix = "with")
    public TaskNameChangedEvent(UUID aggregateId, String newName) {
        super(aggregateId);
        this.newName = newName;
    }
}