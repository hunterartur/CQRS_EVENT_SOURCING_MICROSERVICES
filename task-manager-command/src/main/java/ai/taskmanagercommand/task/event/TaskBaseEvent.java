package ai.taskmanagercommand.task.event;

import ai.taskmanagercommand.eventstore.BaseEvent;
import ai.taskmanagercommand.project.domain.ProjectAggregate;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * Базовый тип event для всех events тасок
 */
@EqualsAndHashCode(callSuper = true)
public class TaskBaseEvent extends BaseEvent {
    /**
     * Тип агрегата по которому вызывается event
     */
    public static final String AGGREGATE_TYPE = ProjectAggregate.AGGREGATE_TYPE;

    public TaskBaseEvent(UUID aggregateId) {
        super(aggregateId);
    }
}
