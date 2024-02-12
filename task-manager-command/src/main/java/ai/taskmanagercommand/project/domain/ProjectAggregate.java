package ai.taskmanagercommand.project.domain;

import ai.taskmanagercommand.eventstore.AggregateRoot;
import ai.taskmanagercommand.eventstore.Event;
import ai.taskmanagercommand.eventstore.SerializerUtil;
import ai.taskmanagercommand.project.event.ProjectCreatedEvent;
import ai.taskmanagercommand.project.event.ProjectNameChangedEvent;
import ai.taskmanagercommand.project.event.TaskToProjectAddedEvent;
import ai.taskmanagercommand.project.event.UserToProjectAddedEvent;
import ai.taskmanagercommand.project.valueobject.Information;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Аггрегат PROJECT
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class ProjectAggregate extends AggregateRoot {
    /**
     * Тип агрегата
     */
    public static final String AGGREGATE_TYPE = "PROJECT";
    /**
     * Информация
     */
    private Information information;
    /**
     * Участники проекты
     */
    private List<UUID> memberIds;
    /**
     * IDs тасок проекта
     */
    private List<UUID> taskIds;

    public ProjectAggregate(UUID id) {
        super(id, AGGREGATE_TYPE);
    }

    @Override
    public void when(Event event) {
        switch (event.getEventType()) {
            case ProjectCreatedEvent.EVENT_TYPE ->
                    process(SerializerUtil.deserializeToJsonBytes(event.getData(), ProjectCreatedEvent.class));
            case ProjectNameChangedEvent.EVENT_TYPE ->
                    process(SerializerUtil.deserializeToJsonBytes(event.getData(), ProjectNameChangedEvent.class));
            case TaskToProjectAddedEvent.EVENT_TYPE ->
                    process(SerializerUtil.deserializeToJsonBytes(event.getData(), TaskToProjectAddedEvent.class));
            case UserToProjectAddedEvent.EVENT_TYPE ->
                    process(SerializerUtil.deserializeToJsonBytes(event.getData(), UserToProjectAddedEvent.class));
        }
    }

    private void process(ProjectCreatedEvent event) {
        memberIds = new ArrayList<>(1);
        this.memberIds.add(event.getUserId());
        this.information = event.getInformation();
    }

    private void process(ProjectNameChangedEvent event) {
        String newName = event.getInformation().getName();
        String description = this.information.getDescription();
        this.information = new Information(newName, description);
    }

    private void process(TaskToProjectAddedEvent event) {
        if (this.taskIds == null) {
            this.taskIds = new ArrayList<>(1);
        }
        this.taskIds.add(event.getTaskId());
    }

    private void process(UserToProjectAddedEvent event) {
        this.memberIds.add(event.getUserId());
    }

    public void createProject(final String name, final UUID userId) {
        final var data = ProjectCreatedEvent.builder()
                .withAggregateId(this.id)
                .withUserId(userId)
                .withInformation(new Information(name))
                .build();
        final var dataBytes = SerializerUtil.serializeToJsonBytes(data);
        final var event = this.createEvent(ProjectCreatedEvent.EVENT_TYPE, dataBytes, null);
        this.apply(event);
    }

    public void changeName(String newName) {
        final var data = ProjectNameChangedEvent.builder()
                .withAggregateId(this.id)
                .withInformation(new Information(newName, this.information.getDescription()))
                .build();
        final var dataBytes = SerializerUtil.serializeToJsonBytes(data);
        final var event = this.createEvent(ProjectNameChangedEvent.EVENT_TYPE, dataBytes, null);
        this.apply(event);
    }

    public void addUser(UUID userId) {
        final var data = UserToProjectAddedEvent.builder()
                .withAggregateId(this.id).withUserId(userId)
                .build();
        final var dataBytes = SerializerUtil.serializeToJsonBytes(data);
        final var event = this.createEvent(UserToProjectAddedEvent.EVENT_TYPE, dataBytes, null);
        this.apply(event);
    }

    public void addTask(UUID taskId) {
        final var data = TaskToProjectAddedEvent.builder()
                .withAggregateId(this.id)
                .withTaskId(taskId)
                .build();
        final var dataBytes = SerializerUtil.serializeToJsonBytes(data);
        final var event = this.createEvent(TaskToProjectAddedEvent.EVENT_TYPE, dataBytes, null);
        this.apply(event);
    }
}
