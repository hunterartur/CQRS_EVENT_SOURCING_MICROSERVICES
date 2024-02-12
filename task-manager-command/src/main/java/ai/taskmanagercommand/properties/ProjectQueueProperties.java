package ai.taskmanagercommand.properties;

import ai.taskmanagercommand.common.RouteKey;

import java.util.HashMap;
import java.util.Map;

public final class ProjectQueueProperties {

    public static final String EXCHANGE = "project";
    public static final String QUEUE = "events";
    public static final Map<RouteKey, String> ROUTE_KEYS = new HashMap<>(Map.of(
            RouteKey.CREATE, "create",
            RouteKey.ADD_USER, "add-user",
            RouteKey.CHANGE_NAME, "change-name",
            RouteKey.ADD_TASK, "add-task"
    ));
}
