package ai.taskmanagercommand.eventstore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface EntityRepository<T, ID> extends JpaRepository<T, ID> {
}
