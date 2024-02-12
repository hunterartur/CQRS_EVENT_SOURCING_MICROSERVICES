package ai.taskmanagercommand.properties;

import ai.taskmanagercommand.common.RouteKey;

import java.util.HashMap;
import java.util.Map;

public final class TaskStatusQueueProperties {
    public static final String EXCHANGE = "task.status";
    public static final String QUEUE = "events";
    public static final Map<RouteKey, String> ROUTE_KEYS = new HashMap<>(Map.of(
            RouteKey.CREATE, "create",
            RouteKey.DELETE, "delete"
    ));
}
