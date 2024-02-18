package ai.taskmanagercommand.eventstore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Парент для всех сущностей в рамках DDD
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class EntityDomainRoot extends IdentifiedDomainObject {
    /**
     * Для определения типа репизитория или мапера
     * @return тип
     */
    public abstract String getType();
}
