package ai.taskmanagerquery.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class SerializeObjectException extends RuntimeException {
    private final String exceptionMessage;
    private final JsonProcessingException exception;
}
