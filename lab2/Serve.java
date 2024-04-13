public class Serve implements Event {
    private final double currentTime;
    private final Customer customer;
    private final Server server;

    public Serve(double currentTime, Customer customer, Server server) {
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

    @Override
    public String type() {
        return "serve";
    }

    @Override
    public Pair<Event, ImList<Server>> nextEvent(ImList<Server> servers) {
        double updatedAvailableTime = this.customer.getArrivalTime() +
                this.customer.getServiceTime();
        Server updatedServer = new Server(this.server.getId(), updatedAvailableTime,
                new Customer(0, 0, 0));
        Done done = new Done(updatedAvailableTime, this.customer, updatedServer);
        servers = servers.set(this.server.getId() - 1, updatedServer);
        return new Pair<>(done, servers);
    }

    @Override
    public String toString() {
        return customer.toString() + " serves by " + server.toString();
    }

}
