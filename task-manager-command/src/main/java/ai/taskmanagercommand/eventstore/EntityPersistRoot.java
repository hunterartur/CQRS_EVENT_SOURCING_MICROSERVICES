package ai.taskmanagercommand.eventstore;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Парент для всех сущностей в рамках DDD для сохранения
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class EntityPersistRoot extends IdentifiedEntityObject {
}
