package ai.taskmanagercommand.eventstore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Событие которое будет отправлено в брокер сообщении
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Table(name = "events")
public class EventEntity {
    /**
     * Идентификатор этого event
     */
    @Id
    @UuidGenerator
    private UUID id;
    /**
     * Идентификатор агрегата
     */
    @Column(name = "aggregate_id")
    private UUID aggregateId;
    /**
     * Тип event
     */
    @Column(name = "event_type")
    private String eventType;
    /**
     * Тип агрегата
     */
    @Column(name = "aggregate_type")
    private String aggregateType;
    /**
     * Версия агрегата
     */
    @Column(name = "version")
    private long version;
    /**
     * Основная информация event(хранит {@link BaseEvent})
     */
    @Column(name = "data")
    private byte[] data;
    /**
     * Метаданные event
     */
    @Column(name = "metadata")
    private byte[] metadata;
    /**
     * Время создания этого event
     */
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
