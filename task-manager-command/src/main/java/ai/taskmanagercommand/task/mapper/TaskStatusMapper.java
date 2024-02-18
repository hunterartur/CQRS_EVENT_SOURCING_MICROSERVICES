package ai.taskmanagercommand.task.mapper;

import ai.taskmanagercommand.eventstore.DomainMapper;
import ai.taskmanagercommand.task.snapshot.TaskStatus;
import org.mapstruct.Mapper;

@Mapper
public interface TaskStatusMapper extends DomainMapper<ai.taskmanagercommand.task.domain.TaskStatus, TaskStatus> {
}
