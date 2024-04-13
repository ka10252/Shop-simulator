interface Event {

    String type();

    double getCurrentTime();

    Customer getCustomer();

    Pair<Event, ImList<Server>> nextEvent(ImList<Server> servers);

    String toString();
}
