package ai.taskmanagercommand.eventstore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Событие которое будет отправлено в брокер сообщении
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Event {
    /**
     * Идентификатор этого event
     */
    private UUID id;
    /**
     * Идентификатор агрегата
     */
    private UUID aggregateId;
    /**
     * Тип event
     */
    private String eventType;
    /**
     * Тип агрегата
     */
    private String aggregateType;
    /**
     * Версия агрегата
     */
    private long version;
    /**
     * Основная информация event(хранит {@link BaseEvent})
     */
    private byte[] data;
    /**
     * Метаданные event
     */
    private byte[] metadata;
    /**
     * Время создания этого event
     */
    private LocalDateTime createdDate;


    public Event(String eventType, String aggregateType) {
        this.id = UUID.randomUUID();
        this.eventType = eventType;
        this.aggregateType = aggregateType;
        this.createdDate = LocalDateTime.now();
    }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", aggregateId='" + aggregateId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", aggregateType='" + aggregateType + '\'' +
                ", version=" + version + '\'' +
                ", timeStamp=" + createdDate + '\'' +
                ", data=" + new String(data) + '\'' +
                '}';
    }
}
