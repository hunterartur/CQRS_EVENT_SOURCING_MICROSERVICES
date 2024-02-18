package ai.taskmanagercommand.project.mapper;

import ai.taskmanagercommand.eventstore.DomainMapper;
import ai.taskmanagercommand.project.snapshot.ProjectInformation;
import ai.taskmanagercommand.project.valueobject.Information;
import org.mapstruct.Mapper;

@Mapper
public interface ProjectInformationMapper extends DomainMapper<ProjectInformation, Information> {
}
