package ai.taskmanagercommand.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Парент для всех сущностей
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public abstract class IdentifiedDomainObject {
    /**
     * ID
     */
    protected UUID id;
}
