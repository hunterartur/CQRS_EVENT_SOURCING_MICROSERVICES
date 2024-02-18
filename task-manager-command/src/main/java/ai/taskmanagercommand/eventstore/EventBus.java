package ai.taskmanagercommand.eventstore;

import ai.taskmanagercommand.common.RouteKey;

import java.util.ArrayList;

public interface EventBus {
    void publish(ArrayList<Event> events, String exchange, RouteKey routeKey);
}
