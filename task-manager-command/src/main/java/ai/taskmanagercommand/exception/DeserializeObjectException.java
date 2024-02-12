package ai.taskmanagercommand.exception;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.IOException;

@ToString
@RequiredArgsConstructor
public class DeserializeObjectException extends RuntimeException {
    private final String exceptionMessage;
    private final IOException exception;
}
