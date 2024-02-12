package ai.taskmanagercommand.project.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Schema(description = "Команда: Добавить пользователя в проект")
@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class AddUserToProjectCommand implements ProjectCommand{

    @Schema(description = "Идентификатор проекта")
    private UUID aggregateId;

    @Schema(description = "Идентификатор пользователя")
    protected UUID userId;
}
