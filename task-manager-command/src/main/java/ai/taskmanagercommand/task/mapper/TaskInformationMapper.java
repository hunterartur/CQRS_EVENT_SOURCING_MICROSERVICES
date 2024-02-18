package ai.taskmanagercommand.task.mapper;

import ai.taskmanagercommand.eventstore.DomainMapper;
import ai.taskmanagercommand.task.snapshot.TaskInformation;
import ai.taskmanagercommand.task.valueobject.Information;
import org.mapstruct.Mapper;

@Mapper
public interface TaskInformationMapper extends DomainMapper<TaskInformation, Information> {
}
