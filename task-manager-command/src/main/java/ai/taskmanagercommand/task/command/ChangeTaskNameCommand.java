package ai.taskmanagercommand.task.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Schema(description = "Команда: Изменить наименования задачи")
@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class ChangeTaskNameCommand {

    @Schema(description = "Идентификатор задачи")
    private UUID taskId;

    @Schema(description = "Новое наименование задачи")
    private String newName;
}