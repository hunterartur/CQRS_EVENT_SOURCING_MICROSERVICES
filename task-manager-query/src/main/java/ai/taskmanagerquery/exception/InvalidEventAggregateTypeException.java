package ai.taskmanagerquery.exception;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
public class InvalidEventAggregateTypeException extends RuntimeException {
    private final String errorMessage;
}
