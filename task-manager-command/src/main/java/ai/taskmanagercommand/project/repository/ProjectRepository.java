package ai.taskmanagercommand.project.repository;

import ai.taskmanagercommand.project.snapshot.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
