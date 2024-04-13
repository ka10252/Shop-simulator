interface Event {

    double getCurrentTime();

    Customer getCustomer();

    Pair<Event, ImList<Server>> nextEvent(ImList<Server> servers);

    String toString();

    boolean isServe();

    boolean isWait();

    boolean isLeave();

    boolean isArrive();

    boolean isDone();

    boolean isTerminal();
}
