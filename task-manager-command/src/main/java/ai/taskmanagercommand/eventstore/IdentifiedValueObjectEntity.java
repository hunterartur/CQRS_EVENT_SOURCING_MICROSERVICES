package ai.taskmanagercommand.eventstore;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Маркуер для value-object которые будут сохранятся
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public abstract class IdentifiedValueObjectEntity extends IdentifiedEntityObject {
}
