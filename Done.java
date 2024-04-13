public class Done implements Event {
    private final double currentTime;
    private final Customer customer;
    private final Server server;

    public Done(double currentTime, Customer customer, Server server) {
        this.currentTime = currentTime;
        this.customer = customer;
        this.server = server;
    }

    @Override
    public double getCurrentTime() {
        return this.currentTime;
    }

    @Override
    public String type() {
        return "done";
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public Pair<Event, ImList<Server>> nextEvent(ImList<Server> servers) {
        return new Pair<>(this, servers);
    }

    @Override
    public String toString() {
        return customer.toStringAft() + " done serving by " + server.toString();
    }
}
