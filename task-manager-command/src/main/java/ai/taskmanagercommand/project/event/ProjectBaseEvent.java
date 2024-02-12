package ai.taskmanagercommand.project.event;

import ai.taskmanagercommand.eventstore.BaseEvent;
import ai.taskmanagercommand.project.domain.ProjectAggregate;
import lombok.EqualsAndHashCode;

import java.util.UUID;

/**
 * Базовый тип project для всех events тасок
 */
@EqualsAndHashCode(callSuper = true)
public class ProjectBaseEvent extends BaseEvent {
    /**
     * Тип агрегата по которому вызывается event
     */
    public static final String AGGREGATE_TYPE = ProjectAggregate.AGGREGATE_TYPE;

    public ProjectBaseEvent(UUID aggregateId) {
        super(aggregateId);
    }
}
