package ai.taskmanagercommand.eventstore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Событие которое будет отправлено в брокер сообщении
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
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
    private byte[] metaData;
    /**
     * Время создания этого event
     */
    private LocalDateTime timeStamp;


    public Event(String eventType, String aggregateType) {
        this.id = UUID.randomUUID();
        this.eventType = eventType;
        this.aggregateType = aggregateType;
        this.timeStamp = LocalDateTime.now();
    }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", aggregateId='" + aggregateId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", aggregateType='" + aggregateType + '\'' +
                ", version=" + version + '\'' +
                ", timeStamp=" + timeStamp + '\'' +
                ", data=" + new String(data) + '\'' +
                '}';
    }
}
