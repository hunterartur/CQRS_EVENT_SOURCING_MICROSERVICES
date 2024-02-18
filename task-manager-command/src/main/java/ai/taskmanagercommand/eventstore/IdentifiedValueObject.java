package ai.taskmanagercommand.eventstore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Парент для всех value-object
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class IdentifiedValueObject extends IdentifiedDomainObject {
}
