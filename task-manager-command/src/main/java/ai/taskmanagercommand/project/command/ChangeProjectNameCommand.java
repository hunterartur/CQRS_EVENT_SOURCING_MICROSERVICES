package ai.taskmanagercommand.project.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Schema(description = "Команда: Изменить имя проекта")
@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class ChangeProjectNameCommand implements ProjectCommand{

    @Schema(description = "Идентификатор проекта")
    private UUID aggregateId;

    @Schema(description = "Новое имя проекта")
    private String newName;
}
