package ai.taskmanagercommand.project.service;

import ai.taskmanagercommand.project.command.ProjectCommand;

import java.text.MessageFormat;
import java.util.List;

public final class ProjectCommandServiceFactory {

    private ProjectCommandServiceFactory() {
    }

    public static <T extends ProjectCommandService<? extends ProjectCommand>> T getProjectCommandService(
            List<ProjectCommandService<? extends ProjectCommand>> services,
            Class<T> serviceClass
    ) {
        return services.stream()
                .filter(serviceClass::isInstance)
                .map(serviceClass::cast)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        MessageFormat.format("Нет такого сервиса {0}",
                                serviceClass.getCanonicalName())));
    }
}
