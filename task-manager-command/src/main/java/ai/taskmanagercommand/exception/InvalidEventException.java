package ai.taskmanagercommand.exception;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class InvalidEventException extends RuntimeException {
    private final String exceptionMessage;
}
