package ai.taskmanagercommand.project.repository;

import ai.taskmanagercommand.eventstore.AggregateRepository;
import ai.taskmanagercommand.project.domain.ProjectAggregate;
import ai.taskmanagercommand.project.snapshot.ProjectPersist;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository(ProjectAggregate.AGGREGATE_TYPE + "_REPO")
public interface ProjectRepository extends AggregateRepository<ProjectPersist, UUID> {
}
