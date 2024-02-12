package ai.taskmanagercommand.task.repository;

import ai.taskmanagercommand.task.shanshot.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, UUID> {
    void deleteById(UUID id);
}
