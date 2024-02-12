package ai.taskmanagercommand.task.shanshot;

import ai.taskmanagercommand.common.IdentifiedEntityObject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Сущность для Snapshot
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task_statuses")
public class TaskStatus extends IdentifiedEntityObject {
    /**
     * Наименование статуса
     */
    @Column(name = "name")
    private String name;
    /**
     * Цвет статуса
     */
    @Column(name = "color")
    private String color;
}
