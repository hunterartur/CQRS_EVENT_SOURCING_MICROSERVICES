package ai.taskmanagercommand.project.event;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

/**
 * Event добавления таски в проект
 */
@EqualsAndHashCode(callSuper = true)
@Getter
public class TaskToProjectAddedEvent extends ProjectBaseEvent {
    /**
     * Тип event
     */
    public static final String EVENT_TYPE = "TASK_TO_PROJECT_ADDED";
    /**
     * ID таски
     */
    private final UUID taskId;

    @Builder(setterPrefix = "with")
    public TaskToProjectAddedEvent(UUID aggregateId, UUID taskId) {
        super(aggregateId);
        this.taskId = taskId;
    }
}
