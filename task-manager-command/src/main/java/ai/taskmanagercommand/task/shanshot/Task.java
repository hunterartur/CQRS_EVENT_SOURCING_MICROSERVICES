package ai.taskmanagercommand.task.shanshot;

import ai.taskmanagercommand.common.IdentifiedEntityObject;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * Сущность для Snapshot
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task extends IdentifiedEntityObject {
    /**
     * Тип агрегата
     */
    @Column(name = "type")
    private String type;
    /**
     * Версия агрегата, нужен для concurrency
     */
    @Column(name = "version")
    private long version;
    /**
     * ID проекта
     */
    @Column(name = "project_id")
    private UUID projectId;
    /**
     * IDs исполнителей
     */
    @ElementCollection(targetClass = UUID.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "task_executors", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "user_id")
    private List<UUID> executors;
    /**
     * Статус таски
     */
    @OneToOne
    @JoinColumn(name = "task_status_id")
    private TaskStatus taskStatus;
    /**
     * Информация
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id")
    private Information information;
}
