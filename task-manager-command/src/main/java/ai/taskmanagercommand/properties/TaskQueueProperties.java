package ai.taskmanagercommand.properties;

import ai.taskmanagercommand.common.RouteKey;

import java.util.HashMap;
import java.util.Map;

public final class TaskQueueProperties {
    public static final String EXCHANGE = "task";
    public static final String QUEUE = "events";
    public static final Map<RouteKey, String> ROUTE_KEYS = new HashMap<>(Map.of(
            RouteKey.CREATE, "create",
            RouteKey.ASSIGN_EXECUTOR, "assign-executor",
            RouteKey.CHANGE_NAME, "change-name",
            RouteKey.CHANGE_STATUS, "change-task-status"
    ));
}
