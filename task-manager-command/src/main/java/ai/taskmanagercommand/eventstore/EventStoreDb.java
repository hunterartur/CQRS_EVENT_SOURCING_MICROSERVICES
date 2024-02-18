package ai.taskmanagercommand.eventstore;

import ai.taskmanagercommand.common.RouteKey;
import ai.taskmanagercommand.exception.AggregateNotFoundException;
import ai.taskmanagercommand.properties.ProjectQueueProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventStoreDb implements EventStore {

    private static final int SNAPSHOT_FREQUENCY = 3;

    private final Map<String, AggregateRepository<? extends AggregateEntityRoot, UUID>> aggregateRepos;

    private final Map<String, DomainMapper<? extends AggregateRoot, ? extends AggregateEntityRoot>> aggregateMappers;

    private final Map<String, EntityRepository<? extends EntityPersistRoot, UUID>> entityRepos;

    private final Map<String, DomainMapper<? extends EntityDomainRoot, ? extends EntityPersistRoot>> entityMappers;

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    private final EventBus eventBus;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public <T extends AggregateRoot> void save(T aggregate) {
        final var events = new ArrayList<>(aggregate.getEvents());

        if (aggregate.getVersion() > 1) {
            this.handleConcurrency(aggregate.getId());
        }

        this.save(events);

        if (aggregate.getVersion() % SNAPSHOT_FREQUENCY == 0) {
            this.createSnapshot(aggregate, aggregate.getType());
        }

        eventBus.publish(events, ProjectQueueProperties.EXCHANGE, RouteKey.CREATE);

        log.info(MessageFormat.format("Агрегат с id={0} сохранен и опубликован в брокер сообщении", aggregate.getId()));
    }

    private void handleConcurrency(UUID id) {

    }

    private <T extends AggregateRoot> void createSnapshot(T aggregate, String aggregateType) {
        var mapper = aggregateMappers.get(aggregateType + "_MAPPER");
        final var snapshot = (AggregateEntityRoot) ((DomainMapper<AggregateRoot, AggregateEntityRoot>) mapper).toSnapshot(aggregate);
        final var repository = aggregateRepos.get(aggregateType + "_REPO");
        repository.getClass().cast(repository).save(snapshot);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void save(List<Event> events) {
        if (events.isEmpty()) {
            log.info("Событий для сохранения нет");
            return;
        }
        final var eventForSave = events.stream().map(eventMapper::toSnapshot).toList();
        eventRepository.saveAll(eventForSave);
        log.info(MessageFormat.format("Событии для агрегата с id={0} сохранены или обновлены", eventForSave.get(0).getAggregateId()));
    }

    @Override
    public <T extends AggregateRoot> T load(UUID aggregateId, Class<T> aggregateClass, String aggregateType) {
        var snapshot = aggregateRepos.get(aggregateType + "_REPO").findByAggregateId(aggregateId);
        final T aggregate;
        if (snapshot.isEmpty()) {
            try {
                aggregate = aggregateClass.getConstructor(UUID.class).newInstance(aggregateId);
            } catch (Exception e) {
                throw new RuntimeException(MessageFormat.format("Не удалось создать экземпляр класса {0}", aggregateClass.getCanonicalName()), e);
            }
        } else {
            var mapper = aggregateMappers.get(aggregateType + "_MAPPER");
            aggregate = aggregateClass.cast(((DomainMapper<AggregateRoot, AggregateEntityRoot>) mapper).toDomain(snapshot.get()));
        }
        final var events = load(aggregateId, aggregate.getVersion());
        aggregate.raiseEvents(events);

        if (aggregate.getVersion() == 0) throw new AggregateNotFoundException(aggregateId);

        log.info("(load) loaded aggregate: {}", aggregate);
        return aggregate;
    }

    @Override
    public List<Event> load(UUID aggregateId, long version) {
        return eventRepository.findByAggregateIdAndVersion(aggregateId, version)
                .stream()
                .map(eventMapper::toDomain)
                .toList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public <T extends EntityDomainRoot> void saveEntity(T entity, String type) {
        final var mapper = (DomainMapper<EntityDomainRoot, EntityPersistRoot>) entityMappers.get(type + "_MAPPER");
        final var repo = entityRepos.get(type + "_REPO");
        repo.getClass().cast(repo).save(mapper.toSnapshot(entity));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public <T extends EntityDomainRoot> void deleteEntity(T entity, String type) {
        entityRepos.get(type + "_REPO").deleteById(entity.getId());
    }
}
