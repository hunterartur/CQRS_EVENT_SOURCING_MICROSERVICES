package ai.taskmanagercommand.task.domain;

import ai.taskmanagercommand.eventstore.AggregateRoot;
import ai.taskmanagercommand.eventstore.Event;
import ai.taskmanagercommand.eventstore.SerializerUtil;
import ai.taskmanagercommand.task.event.TaskCreatedEvent;
import ai.taskmanagercommand.task.event.TaskExecutorAssignedEvent;
import ai.taskmanagercommand.task.event.TaskNameChangedEvent;
import ai.taskmanagercommand.task.event.TaskStatusChangedEvent;
import ai.taskmanagercommand.task.valueobject.Information;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Аггрегат TASK
 */
public class TaskAggregate extends AggregateRoot {
    /**
     * Тип агрегата
     */
    public static final String AGGREGATE_TYPE = "TASK";
    /**
     * ID проекта
     */
    private UUID projectId;
    /**
     * IDs исполнителей
     */
    private List<UUID> executors;
    /**
     * Статус таски
     */
    private TaskStatus taskStatus;
    /**
     * Информация
     */
    private Information information;

    public TaskAggregate(UUID id) {
        super(id, AGGREGATE_TYPE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void when(Event event) {
        switch (event.getEventType()) {
            case TaskCreatedEvent.EVENT_TYPE ->
                    process(SerializerUtil.deserializeToJsonBytes(event.getData(), TaskCreatedEvent.class));
            case TaskExecutorAssignedEvent.EVENT_TYPE ->
                    process(SerializerUtil.deserializeToJsonBytes(event.getData(), TaskExecutorAssignedEvent.class));
            case TaskNameChangedEvent.EVENT_TYPE ->
                    process(SerializerUtil.deserializeToJsonBytes(event.getData(), TaskNameChangedEvent.class));
            case TaskStatusChangedEvent.EVENT_TYPE ->
                    process(SerializerUtil.deserializeToJsonBytes(event.getData(), TaskStatusChangedEvent.class));
        }
    }

    private void process(TaskCreatedEvent event) {
        this.projectId = event.getProjectId();
        this.information = event.getInformation();
        this.taskStatus = event.getTaskStatus();
    }

    private void process(TaskExecutorAssignedEvent event) {
        if (this.executors == null) {
            this.executors = new ArrayList<>(1);
        }
        this.executors.add(event.getUserId());
    }

    private void process(TaskNameChangedEvent event) {
        var newName = event.getNewName();
        var description = this.information.getDescription();
        this.information = new Information(newName, description);
    }

    private void process(TaskStatusChangedEvent event) {
        this.taskStatus = event.getTaskStatus();
    }

    public void createTask(final UUID projectId, final String name) {
        createTask(projectId, name, null);
    }

    public void createTask(final UUID projectId, final String name, TaskStatus taskStatus) {
        final var data = TaskCreatedEvent.builder()
                .withAggregateId(this.id)
                .withProjectId(projectId)
                .withInformation(new Information(name))
                .withTaskStatus(taskStatus)
                .build();
        final var dataBytes = SerializerUtil.serializeToJsonBytes(data);
        final var event = this.createEvent(TaskCreatedEvent.EVENT_TYPE, dataBytes, null);
        this.apply(event);
    }

    public void changeName(String newName) {
        final var data = TaskNameChangedEvent.builder()
                .withAggregateId(this.id)
                .withNewName(newName)
                .build();
        final var dataBytes = SerializerUtil.serializeToJsonBytes(data);
        final var event = this.createEvent(TaskNameChangedEvent.EVENT_TYPE, dataBytes, null);
        this.apply(event);
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        final var data = TaskStatusChangedEvent.builder()
                .withAggregateId(this.id)
                .withTaskStatus(taskStatus)
                .build();
        final var dataBytes = SerializerUtil.serializeToJsonBytes(data);
        final var event = this.createEvent(TaskStatusChangedEvent.EVENT_TYPE, dataBytes, null);
        this.apply(event);
    }

    public void addExecutor(UUID userId) {
        final var data = TaskExecutorAssignedEvent.builder()
                .withAggregateId(this.id)
                .withUserId(userId)
                .build();
        final var dataBytes = SerializerUtil.serializeToJsonBytes(data);
        final var event = this.createEvent(TaskExecutorAssignedEvent.EVENT_TYPE, dataBytes, null);
        this.apply(event);
    }
}
