package ai.taskmanagercommand.project.valueobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Value-object
 */
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Information implements Serializable {
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
