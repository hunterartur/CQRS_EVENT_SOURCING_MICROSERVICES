package ai.taskmanagercommand.project.controller;

import ai.taskmanagercommand.project.command.AddTaskToProjectCommand;
import ai.taskmanagercommand.project.command.AddUserToProjectCommand;
import ai.taskmanagercommand.project.command.ChangeProjectNameCommand;
import ai.taskmanagercommand.project.command.CreateProjectCommand;
import ai.taskmanagercommand.project.command.ProjectCommand;
import ai.taskmanagercommand.project.service.AddTaskToProjectCommandService;
import ai.taskmanagercommand.project.service.AddUserToProjectCommandService;
import ai.taskmanagercommand.project.service.ChangeProjectNameCommandService;
import ai.taskmanagercommand.project.service.CreateProjectCommandService;
import ai.taskmanagercommand.project.service.ProjectCommandService;
import ai.taskmanagercommand.project.service.ProjectCommandServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final List<ProjectCommandService<? extends ProjectCommand>> commandServices;

    @PostMapping("/add-task-to-project")
    public ResponseEntity<HttpStatus> addTaskToProject(
            @RequestParam("aggregateId") UUID aggregateId,
            @RequestParam("taskId") UUID taskId
    ) {
        ProjectCommandServiceFactory.getProjectCommandService(commandServices, AddTaskToProjectCommandService.class)
                .process(new AddTaskToProjectCommand(aggregateId, taskId));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create-project")
    public ResponseEntity<HttpStatus> createProject(
            @RequestParam("aggregateId") UUID aggregateId,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("userId") UUID userId
    ) {
        ProjectCommandServiceFactory.getProjectCommandService(commandServices, CreateProjectCommandService.class)
                .process(new CreateProjectCommand(aggregateId, name, description, userId));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/change-project-name")
    public ResponseEntity<HttpStatus> changeProjectName(
            @RequestParam("aggregateId") UUID aggregateId,
            @RequestParam("name") String name
    ) {
        ProjectCommandServiceFactory.getProjectCommandService(commandServices, ChangeProjectNameCommandService.class)
                .process(new ChangeProjectNameCommand(aggregateId, name));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-user-to-project")
    public ResponseEntity<HttpStatus> addUserToProject(
            @RequestParam("aggregateId") UUID aggregateId,
            @RequestParam("userId") UUID userId
    ) {
        ProjectCommandServiceFactory.getProjectCommandService(commandServices, AddUserToProjectCommandService.class)
                .process(new AddUserToProjectCommand(aggregateId, userId));
        return ResponseEntity.ok().build();
    }
}
