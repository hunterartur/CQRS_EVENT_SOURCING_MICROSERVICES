package ai.taskmanagercommand.project.snapshot;

import ai.taskmanagercommand.common.IdentifiedEntityObject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * Сущность для сохранения Value-object {@link ai.taskmanagercommand.project.valueobject.Information}
 */
@NoArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "project_information")
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
