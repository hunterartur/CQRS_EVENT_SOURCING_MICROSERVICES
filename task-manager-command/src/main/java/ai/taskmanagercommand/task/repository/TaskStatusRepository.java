package ai.taskmanagercommand.task.repository;

import ai.taskmanagercommand.eventstore.EntityRepository;
import ai.taskmanagercommand.task.snapshot.TaskStatus;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("TASK_STATUS_REPO")
public interface TaskStatusRepository extends EntityRepository<TaskStatus, UUID> {
    void deleteById(UUID id);
}
