package ai.taskmanagercommand.task.event;

import ai.taskmanagercommand.task.domain.TaskStatus;
import ai.taskmanagercommand.task.valueobject.Information;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

/**
 * Event создания таски
 */
@EqualsAndHashCode(callSuper = true)
@Getter
public class TaskCreatedEvent extends TaskBaseEvent {
    /**
     * Тип event
     */
    public static final String EVENT_TYPE = "TASK_CREATED";
    /**
     * ID проекта
     */
    private final UUID projectId;
    /**
     * Описание проекта
     */
    private final Information information;
    /**
     * Статус таски
     */
    private final TaskStatus taskStatus;

    @Builder(setterPrefix = "with")
    public TaskCreatedEvent(UUID aggregateId, UUID projectId, Information information, TaskStatus taskStatus) {
        super(aggregateId);
        this.projectId = projectId;
        this.information = information;
        this.taskStatus = taskStatus;
    }
}