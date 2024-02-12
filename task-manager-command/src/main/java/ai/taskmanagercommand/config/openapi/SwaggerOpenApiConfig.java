package ai.taskmanagercommand.config.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.stereotype.Component;

@OpenAPIDefinition(info = @Info(title = "Spring CQRS and Event Sourcing Microservice",
        description = "Spring Postgresql MongoDB RabbitMQ CQRS and Event Sourcing Microservice",
        contact = @Contact(name = "Arthur Ishmaev", url = "https://github.com/hunterartur")))
@Component
public class SwaggerOpenApiConfig {
}
