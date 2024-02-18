package ai.taskmanagercommand.eventstore;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

/**
 * Парент для всех сущностей которые будут сохранены в БД
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@MappedSuperclass
public abstract class IdentifiedEntityObject {
    /**
     * ID
     */
    @Id
    @UuidGenerator
    protected UUID id;
}
