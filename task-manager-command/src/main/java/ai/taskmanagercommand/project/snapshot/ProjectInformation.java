package ai.taskmanagercommand.project.snapshot;

import ai.taskmanagercommand.eventstore.IdentifiedValueObjectEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Сущность для сохранения Value-object {@link ai.taskmanagercommand.project.valueobject.Information}
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "project_information")
public class ProjectInformation extends IdentifiedValueObjectEntity implements Serializable {
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

    public ProjectInformation(String name) {
        this.name = name;
    }

    public ProjectInformation(String name, String description) {
        this(name);
        this.description = description;
    }
}
