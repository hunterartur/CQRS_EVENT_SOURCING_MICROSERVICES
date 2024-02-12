package ai.taskmanagercommand.task.domain;

import ai.taskmanagercommand.common.IdentifiedDomainObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Сущность
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatus extends IdentifiedDomainObject {
    /**
     * Наименование статуса
     */
    private String name;
    /**
     * Цвет статуса
     */
    private String color;
}
