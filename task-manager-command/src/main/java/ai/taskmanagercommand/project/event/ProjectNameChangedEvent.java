package ai.taskmanagercommand.project.event;

import ai.taskmanagercommand.project.valueobject.Information;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

/**
 * Event изменения имени проекта
 */
@EqualsAndHashCode(callSuper = true)
@Getter
public class ProjectNameChangedEvent extends ProjectBaseEvent {
    /**
     * Тип event
     */
    public static final String EVENT_TYPE = "PROJECT_NAME_CHANGED";
    /**
     * Информация о проекте(имя, описание)
     */
    private final Information information;

    @Builder(setterPrefix = "with")
    public ProjectNameChangedEvent(UUID aggregateId, Information information) {
        super(aggregateId);
        this.information = information;
    }
}
