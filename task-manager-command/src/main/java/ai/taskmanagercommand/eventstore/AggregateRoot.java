package ai.taskmanagercommand.eventstore;

import ai.taskmanagercommand.exception.InvalidEventAggregateTypeException;
import ai.taskmanagercommand.exception.InvalidEventException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Парент для всех агрегатов, содержит основные методы для работы агрегатов
 * Например: load, apply, raiseEvent
 */
@ToString
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public abstract class AggregateRoot extends IdentifiedDomainObject {
    /**
     * Тип агрегата
     */
    protected String type;
    /**
     * Версия агрегата, нужен для concurrency
     */
    protected long version;
    /**
     * Содержит список всех событий произошедших с агрегатом
     */
    protected final List<Event> events = new ArrayList<>();

    public AggregateRoot(final UUID id, final String aggregateType) {
        this.id = id;
        this.type = aggregateType;
    }

    /**
     * Обрабатывает event, в зависимости event.eventType
     */
    public abstract void when(final Event event);

    /**
     * @return тип агрегата
     */
    public abstract String getType();

    /**
     * Применяет список событии к агрегату, сохраняя event(добавляет в this.events)
     */
    public void applyEvents(final List<Event> events) {
        events.forEach(this::apply);
    }

    /**
     * Применяет событие к агрегату, сохраняя event(добавляет в this.events)
     */
    public void apply(final Event event) {
        this.raiseEvent(event);
        event.setVersion(this.version);
        events.add(event);
    }

    /**
     * Применяет спсиок событии к агрегату, без добавления event в this.events
     */
    public void raiseEvents(List<Event> events) {
        events.forEach(this::raiseEvent);
    }

    /**
     * Применяет event к агрегату, без добавления event в this.events
     */
    public void raiseEvent(final Event event) {
        this.validateEvent(event);
        when(event);
        this.version++;
    }

    /**
     * Очишает список events
     */
    public void clearEvents() {
        this.events.clear();
    }

    /**
     * Очишает список events, так как для snapshot они не нужны
     * в snapshot храним только состояние агрегата на момент создания snapshot
     */
    public void toSnapshot() {
        this.clearEvents();
    }

    /**
     * Создает Event для отправки в брокер сообщении
     *
     * @param eventType тип event
     * @param data      основная информация события(хранит {@link BaseEvent})
     * @param metadata  метаданные event
     * @return event для отправки в брокер сообщении
     */
    protected Event createEvent(String eventType, byte[] data, byte[] metadata) {
        return Event.builder()
                .aggregateId(this.id)
                .version(this.version)
                .aggregateType(this.type)
                .eventType(eventType)
                .data(data == null ? new byte[]{} : data)
                .metadata(metadata == null ? new byte[]{} : metadata)
                .createdDate(LocalDateTime.now())
                .build();
    }

    private void validateEvent(final Event event) {
        if (event == null) {
            throw new InvalidEventException("Event не должен быть NULL");
        }
        if (!event.getAggregateId().equals(this.id)) {
            throw new InvalidEventException(MessageFormat.format("Не верный ID агрегата у event. Предоставили: {0}, ожидалось: {1}", event.getAggregateId(), this.id));
        }
        if (event.getAggregateType() == null) {
            throw new InvalidEventException("Тип агрегата у event не должен быть NULL");
        }
        if (!event.getAggregateType().equals(this.type)) {
            throw new InvalidEventAggregateTypeException(MessageFormat.format("Не верный тип агрегата у event. Предоставили: {0}, ожидалось: {1}", event.getAggregateId(), this.type));
        }
    }
}
