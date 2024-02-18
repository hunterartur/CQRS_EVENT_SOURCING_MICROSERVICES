package ai.taskmanagercommand.eventstore;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

/**
 * Парент для всех агрегатов которые будут сохранены
 */
@MappedSuperclass
public class AggregateEntityRoot extends IdentifiedEntityObject {
    /**
     * Тип агрегата
     */
    @Column(name = "type")
    protected String type;
    /**
     * Версия агрегата, нужен для concurrency
     */
    @Column(name = "version")
    protected long version;
}
