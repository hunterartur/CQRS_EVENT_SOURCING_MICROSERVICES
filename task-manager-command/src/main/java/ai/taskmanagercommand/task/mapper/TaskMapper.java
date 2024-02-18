package ai.taskmanagercommand.task.mapper;

import ai.taskmanagercommand.eventstore.DomainMapper;
import ai.taskmanagercommand.eventstore.EventMapper;
import ai.taskmanagercommand.task.domain.TaskAggregate;
import ai.taskmanagercommand.task.snapshot.TaskPersist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {TaskStatusMapper.class, TaskInformationMapper.class, EventMapper.class}, implementationName = TaskAggregate.AGGREGATE_TYPE + "_MAPPER")
public interface TaskMapper extends DomainMapper<TaskAggregate, TaskPersist> {

    @Mapping(target = "aggregateId", source = "id")
    @Mapping(target = "id", ignore = true)
    @Override
    TaskPersist toSnapshot(TaskAggregate aggregate);

    @Mapping(target = "id", source = "aggregateId")
    @Override
    TaskAggregate toDomain(TaskPersist snapshot);
}
