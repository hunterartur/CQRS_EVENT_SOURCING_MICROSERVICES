package ai.taskmanagercommand.project.mapper;

import ai.taskmanagercommand.eventstore.DomainMapper;
import ai.taskmanagercommand.eventstore.EventMapper;
import ai.taskmanagercommand.project.domain.ProjectAggregate;
import ai.taskmanagercommand.project.snapshot.ProjectPersist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ProjectInformationMapper.class, EventMapper.class}, implementationName = ProjectAggregate.AGGREGATE_TYPE + "_MAPPER")
public interface ProjectMapper extends DomainMapper<ProjectAggregate, ProjectPersist> {

    @Mapping(target = "aggregateId", source = "id")
    @Mapping(target = "id", ignore = true)
    @Override
    ProjectPersist toSnapshot(ProjectAggregate aggregate);

    @Mapping(target = "id", source = "aggregateId")
    @Override
    ProjectAggregate toDomain(ProjectPersist snapshot);
}
