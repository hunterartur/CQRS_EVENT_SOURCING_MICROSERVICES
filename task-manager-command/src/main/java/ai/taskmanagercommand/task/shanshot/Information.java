package ai.taskmanagercommand.task.shanshot;

import ai.taskmanagercommand.common.IdentifiedEntityObject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Сущность для сохранения Value-object {@link ai.taskmanagercommand.task.valueobject.Information}
 */
@NoArgsConstructor
@Getter
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(name = "task_information")
public class Information extends IdentifiedEntityObject implements Serializable {
    /**
     * Наименование
     */
    @Column(name = "name")
    private String name;
    /**
     * Описание
     */
    @Column(name = "description")
    private String description;

    public Information(String name) {
        this.name = name;
    }

    public Information(String name, String description) {
        this(name);
        this.description = description;
    }
}
