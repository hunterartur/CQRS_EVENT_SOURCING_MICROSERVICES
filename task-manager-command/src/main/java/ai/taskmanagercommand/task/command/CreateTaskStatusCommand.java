package ai.taskmanagercommand.task.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Schema(description = "Команда: Создать статус задачи")
@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskStatusCommand {

    @Schema(description = "Наименование статуса")
    private String name;

    @Schema(description = "Цвет отображения статуса")
    private String color;
}
