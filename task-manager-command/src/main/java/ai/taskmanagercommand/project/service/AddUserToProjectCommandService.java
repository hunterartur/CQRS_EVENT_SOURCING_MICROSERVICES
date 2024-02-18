package ai.taskmanagercommand.project.service;

import ai.taskmanagercommand.eventstore.EventStore;
import ai.taskmanagercommand.project.command.AddUserToProjectCommand;
import ai.taskmanagercommand.project.domain.ProjectAggregate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddUserToProjectCommandService extends ProjectCommandService<AddUserToProjectCommand> {

    private final EventStore eventStore;

    @Override
    public void process(AddUserToProjectCommand command) {
        checkCommand(command);
        final var aggregate = eventStore.load(command.getAggregateId(), ProjectAggregate.class, ProjectAggregate.AGGREGATE_TYPE);
        aggregate.addUser(command.getUserId());
        eventStore.save(aggregate);
        log.info(MessageFormat.format(
                "В проект с id={0} добавлен пользователь с id={1}",
                aggregate.getId(), command.getUserId())
        );
    }

    @Override
    protected void checkCommand(AddUserToProjectCommand command) {
        if (command == null) {
            throwExceptionForNull("Команда");
        }
        if (command.getAggregateId() == null) {
            throwExceptionForNull("ID проекта");
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
