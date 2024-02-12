package ai.taskmanagercommand.project.service;

import ai.taskmanagercommand.eventstore.EventStore;
import ai.taskmanagercommand.project.command.CreateProjectCommand;
import ai.taskmanagercommand.project.domain.ProjectAggregate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateProjectCommandService extends ProjectCommandService<CreateProjectCommand> {

    private final EventStore eventStore;

    @Override
    public void process(CreateProjectCommand command) {
        checkCommand(command);
        final var aggregate = new ProjectAggregate(command.getAggregateId());
        aggregate.createProject(command.getName(), command.getUserId());
        eventStore.save(aggregate);
        log.info(
                MessageFormat.format("Агрегат с id={0} успешно создан!", aggregate.getId()));
    }

    @Override
    public String getCommandType() {
        return CreateProjectCommand.class.getSimpleName();
    }

    @Override
    protected void checkCommand(CreateProjectCommand command) {
        if (command == null) {
            throwExceptionForNull("Команда");
        }
        if (command.getAggregateId() == null) {
            throwExceptionForNull("ID агрегата");
        }
        if (command.getName() == null) {
            throwExceptionForNull("Имя агрегата");
        }
        if (command.getUserId() == null) {
            throwExceptionForNull("ID пользователя");
        }
    }

    private static void throwExceptionForNull(String name) {
        throw new RuntimeException(
                MessageFormat.format("{0} не может быть NULL. Это обязательный параметр!", name));
    }
}
