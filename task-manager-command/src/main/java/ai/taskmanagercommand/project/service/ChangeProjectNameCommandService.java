package ai.taskmanagercommand.project.service;

import ai.taskmanagercommand.eventstore.EventStore;
import ai.taskmanagercommand.project.command.ChangeProjectNameCommand;
import ai.taskmanagercommand.project.domain.ProjectAggregate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChangeProjectNameCommandService extends ProjectCommandService<ChangeProjectNameCommand> {

    private final EventStore eventStore;

    @Override
    public void process(ChangeProjectNameCommand command) {
        checkCommand(command);
        final var aggregate = eventStore.load(command.getAggregateId(), ProjectAggregate.class, ProjectAggregate.AGGREGATE_TYPE);
        aggregate.changeName(command.getNewName());
        eventStore.save(aggregate);
        log.info(MessageFormat.format(
                        "Имя проекта с id={0} изменено на {1}",
                        aggregate.getId(), aggregate.getProjectInformation().getName())
        );
    }

    @Override
    public String getCommandType() {
        return ChangeProjectNameCommand.class.getSimpleName();
    }

    @Override
    protected void checkCommand(ChangeProjectNameCommand command) {
        if (command == null) {
            throwExceptionForNull("Команда");
        }
        if (command.getAggregateId() == null) {
            throwExceptionForNull("ID проекта");
        }
        if (command.getNewName() == null) {
            throwExceptionForNull("Имя агрегата");
        }
    }

    private static void throwExceptionForNull(String name) {
        throw new RuntimeException(
                MessageFormat.format("{0} не может быть NULL. Это обязательный параметр!", name));
    }
}
