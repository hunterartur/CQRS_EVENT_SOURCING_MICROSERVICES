package ai.taskmanagercommand.eventstore;

import org.mapstruct.Mapper;

@Mapper
public interface EventMapper {
    EventEntity toSnapshot(Event domain);

    Event toDomain(EventEntity snapshot);
}
