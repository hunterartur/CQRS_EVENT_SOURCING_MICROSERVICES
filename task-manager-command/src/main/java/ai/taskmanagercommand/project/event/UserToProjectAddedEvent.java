package ai.taskmanagercommand.project.event;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

/**
 * Event создания проекта
 */
@EqualsAndHashCode(callSuper = true)
@Getter
public class UserToProjectAddedEvent extends ProjectBaseEvent {
    /**
     * Тип event
     */
    public static final String EVENT_TYPE = "USER_TO_PROJECT_ADDED";
    /**
     * ID пользователя
     */
    private final UUID userId;

    @Builder(setterPrefix = "with")
    public UserToProjectAddedEvent(UUID aggregateId, UUID userId) {
        super(aggregateId);
        this.userId = userId;
    }
}
