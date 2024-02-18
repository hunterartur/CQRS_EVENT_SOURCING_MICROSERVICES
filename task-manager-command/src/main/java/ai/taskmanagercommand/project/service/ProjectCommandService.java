package ai.taskmanagercommand.project.service;

import ai.taskmanagercommand.project.command.ProjectCommand;
import jakarta.validation.constraints.NotNull;

public abstract class ProjectCommandService<T extends ProjectCommand> {
    public abstract void process(T command);

    protected abstract void checkCommand(@NotNull T command);
}
