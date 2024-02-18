package ai.taskmanagercommand.task.valueobject;

import ai.taskmanagercommand.eventstore.IdentifiedValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Value-object
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Information extends IdentifiedValueObject implements Serializable {
    /**
     * Наименование
     */
    private String name;
    /**
     * Описание
     */
    private String description;

    public Information(String name) {
        this.name = name;
    }

    public Information(String name, String description) {
        this(name);
        this.description = description;
    }
}
