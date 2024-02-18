package ai.taskmanagercommand.eventstore;

public interface DomainMapper<D, S> {

    S toSnapshot(D domain);

    D toDomain(S snapshot);
}
