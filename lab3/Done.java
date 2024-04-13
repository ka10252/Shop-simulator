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
    public Customer getCustomer() {
        return customer;
    }

    public boolean isServe() {
        return false;
    }

    public boolean isWait() {
        return false;
    }

    public boolean isLeave() {
        return false;
    }

    public boolean isArrive() {
        return false;
    }

    public boolean isDone() {
        return true;
    }

    public boolean isTerminal() {
        return true;
    }

    @Override
    public Pair<Event, ImList<Server>> nextEvent(ImList<Server> servers) {
        return new Pair<>(this, servers);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d", this.currentTime,
                this.customer.getCustomerId()) + " done serving by "
                + server.toString() + "\n";
    }
}
