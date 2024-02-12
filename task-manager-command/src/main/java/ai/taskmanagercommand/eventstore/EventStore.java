package ai.taskmanagercommand.eventstore;

import java.util.List;
import java.util.UUID;

public interface EventStore {
    <T extends AggregateRoot> void save(final T aggregate);

    void save(final List<Event> events);

    <T extends AggregateRoot> T load(final UUID aggregateId, final Class<T> aggregateType);

    List<Event> load(final UUID aggregateId, long version);
}
