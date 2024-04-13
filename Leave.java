public class Leave implements Event {
    private final double currentTime;
    private final Customer customer;

    public Leave(double currentTime, Customer customer) {
        this.currentTime = currentTime;
        this.customer = customer;
    }

    @Override
    public double getCurrentTime() {
        return this.currentTime;
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String type() {
        return "leave";
    }

    @Override
    public Pair<Event, ImList<Server>> nextEvent(ImList<Server> servers) {
        return new Pair<>(this, servers);
    }

    @Override
    public String toString() {
        return customer.toString() + " leaves";
    }
}
