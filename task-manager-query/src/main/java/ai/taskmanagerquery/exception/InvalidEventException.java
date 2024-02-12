package ai.taskmanagerquery.exception;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class InvalidEventException extends RuntimeException {
    private final String exceptionMessage;
}
