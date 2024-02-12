package ai.taskmanagercommand.eventstore;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventStoreDb implements EventStore {

    @Override
    public <T extends AggregateRoot> void save(T aggregate) {

    }

    @Override
    public void save(List<Event> events) {

    }

    @Override
    public <T extends AggregateRoot> T load(UUID aggregateId, Class<T> aggregateType) {
        return null;
    }

    @Override
    public List<Event> load(UUID aggregateId, long version) {
        return null;
    }
}
