package ai.taskmanagercommand.common;

public enum RouteKey {
    CREATE("create"),
    CHANGE_NAME("change-name"),
    CHANGE_STATUS("change-task-status"),
    ADD_TASK("add-task"),
    ADD_USER("add-user"),
    DELETE("delete"),
    ASSIGN_EXECUTOR("assign-executor");

    private String name;

    RouteKey(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
