package ai.taskmanagerquery.project.domain;

import ai.taskmanagerquery.eventstore.IdentifiedDomainObject;
import ai.taskmanagerquery.eventstore.Information;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

/**
 * Сущность
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "projects")
public class Project extends IdentifiedDomainObject {
    /**
     * ID
     */
    @BsonProperty(value = "id")
    private UUID id;
    /**
     * ID агрегата
     */
    @BsonProperty(value = "aggregate_id")
    private String aggregateId;
    /**
     * Информация
     */
    @BsonProperty(value = "information")
    private Information information;
    /**
     * Участники проекты
     */
    @BsonProperty(value = "member_ids")
    private List<UUID> memberIds;
    /**
     * IDs тасок проекта
     */
    @BsonProperty(value = "task_ids")
    private List<UUID> taskIds;
}
