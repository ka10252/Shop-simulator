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

    public boolean isServe() {
        return false;
    }

    public boolean isWait() {
        return false;
    }

    public boolean isLeave() {
        return true;
    }

    public boolean isArrive() {
        return false;
    }

    public boolean isDone() {
        return false;
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
        return customer.toString() + " leaves\n";
    }
}
