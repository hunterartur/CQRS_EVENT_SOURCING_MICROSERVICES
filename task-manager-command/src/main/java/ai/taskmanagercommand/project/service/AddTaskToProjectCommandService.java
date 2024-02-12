package ai.taskmanagercommand.project.service;

import ai.taskmanagercommand.eventstore.EventStore;
import ai.taskmanagercommand.project.command.AddTaskToProjectCommand;
import ai.taskmanagercommand.project.domain.ProjectAggregate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddTaskToProjectCommandService extends ProjectCommandService<AddTaskToProjectCommand> {

    private final EventStore eventStore;

    @Override
    public void process(AddTaskToProjectCommand command) {
        checkCommand(command);
        final var aggregate = eventStore.load(command.getAggregateId(), ProjectAggregate.class);
        aggregate.addTask(command.getTaskId());
        eventStore.save(aggregate);
        log.info(MessageFormat.format(
                "В проект с id={0} добавлена таска с id={1}",
                aggregate.getId(), command.getTaskId())
        );
    }

    @Override
    public String getCommandType() {
        return AddTaskToProjectCommand.class.getSimpleName();
    }

    @Override
    protected void checkCommand(AddTaskToProjectCommand command) {
        if (command == null) {
            throwExceptionForNull("Команда");
        }
        if (command.getAggregateId() == null) {
            throwExceptionForNull("ID проекта");
        }
        if (command.getTaskId() == null) {
            throwExceptionForNull("ID таски");
        }
    }

    private static void throwExceptionForNull(String name) {
        throw new RuntimeException(
                MessageFormat.format("{0} не может быть NULL. Это обязательный параметр!", name));
    }
}
