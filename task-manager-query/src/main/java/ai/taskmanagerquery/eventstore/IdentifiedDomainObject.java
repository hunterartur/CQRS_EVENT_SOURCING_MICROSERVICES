package ai.taskmanagerquery.eventstore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

/**
 * Парент для всех сущностей которые будут сохранены в БД
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class IdentifiedDomainObject {
    /**
     * Идентификатор агрегата
     */
    @BsonProperty("id")
    protected UUID id;
}
