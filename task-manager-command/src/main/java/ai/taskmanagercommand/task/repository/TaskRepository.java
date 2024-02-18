package ai.taskmanagercommand.task.repository;

import ai.taskmanagercommand.eventstore.AggregateRepository;
import ai.taskmanagercommand.task.domain.TaskAggregate;
import ai.taskmanagercommand.task.snapshot.TaskPersist;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository(TaskAggregate.AGGREGATE_TYPE + "_REPO")
public interface TaskRepository extends AggregateRepository<TaskPersist, UUID> {
}
