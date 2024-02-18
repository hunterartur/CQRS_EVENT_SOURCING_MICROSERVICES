package ai.taskmanagercommand.eventstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<EventEntity, UUID> {

    @Query("SELECT e From EventEntity e WHERE e.aggregateId = ?1 AND e.version > ?2 ORDER BY e.version ASC")
    List<EventEntity> findByAggregateIdAndVersion(UUID aggregateId, long version);
}
