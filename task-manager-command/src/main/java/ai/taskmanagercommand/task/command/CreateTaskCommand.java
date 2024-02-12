package ai.taskmanagercommand.task.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Schema(description = "Команда: Создать задачу")
@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskCommand {

    @Schema(description = "Идентификатор проекта")
    private UUID projectId;

    @Schema(description = "Наименование задачи")
    private String name;

    @Schema(description = "Описание задачи")
    private String description;

    @Schema(description = "Идентификатор статуса задачи")
    private UUID taskStatusId;

    @Schema(description = "Идентификатор пользователя создавшего задачу")
    private UUID userId;
}