package ai.taskmanagercommand.project.controller;

import ai.taskmanagercommand.project.command.AddTaskToProjectCommand;
import ai.taskmanagercommand.project.command.ProjectCommand;
import ai.taskmanagercommand.project.service.AddTaskToProjectCommandService;
import ai.taskmanagercommand.project.service.ProjectCommandService;
import ai.taskmanagercommand.project.service.ProjectCommandServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final List<ProjectCommandService<? extends ProjectCommand>> commandServices;

    @GetMapping("/test")
    public ResponseEntity<HttpStatus> test() {
        ProjectCommandServiceFactory.getProjectCommandService(commandServices, AddTaskToProjectCommandService.class)
                .process(new AddTaskToProjectCommand(UUID.randomUUID(), UUID.randomUUID()));
        return ResponseEntity.ok().build();
    }
}
