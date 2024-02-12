package ai.taskmanagercommand.task.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Schema(description = "Команда: Добавить исполнителя задачи")
@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class AssignExecutorToTaskCommand {

    @Schema(description = "Идентификатор задчи")
    private UUID taskId;

    @Schema(description = "Идентификатор пользователя")
    private UUID userId;
}
