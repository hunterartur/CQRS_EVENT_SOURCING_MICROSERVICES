package ai.taskmanagercommand.project.event;

import ai.taskmanagercommand.project.valueobject.Information;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

/**
 * Event создания проекта
 */
@EqualsAndHashCode(callSuper = true)
@Getter
public class ProjectCreatedEvent extends ProjectBaseEvent {
    /**
     * Тип event
     */
    public static final String EVENT_TYPE = "PROJECT_CREATED";
    /**
     * ID пользователя
     */
    private final UUID userId;
    /**
     * Информация о проекте(имя, описание)
     */
    private final Information information;

    @Builder(setterPrefix = "with")
    public ProjectCreatedEvent(UUID aggregateId, UUID userId, Information information) {
        super(aggregateId);
        this.userId = userId;
        this.information = information;
    }
}
