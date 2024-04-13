public class Serve implements Event {
    private final double currentTime;
    private final Customer customer;
    private final Server server;
    private final int qmax;

    public Serve(double currentTime, Customer customer, Server server, int qmax) {
        this.currentTime = currentTime;
        this.customer = customer;
        this.server = server;
        this.qmax = qmax;
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
        return true;
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
        return false;
    }

    public boolean isTerminal() {
        return false;
    }

    @Override
    public Pair<Event, ImList<Server>> nextEvent(ImList<Server> servers) {
        Server currentServer = servers.get(this.server.getServerId() - 1);
        Server updatedServer = new Server(this.server.getServerId(),
                currentServer.getAvailableTime(), this.customer, qmax);
        Done done = new Done(currentServer.getAvailableTime(), this.customer, updatedServer);
        ImList<Server> updatedServers = servers.set(this.server.getServerId() - 1, updatedServer);
        return new Pair<>(done, updatedServers);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d", this.currentTime,
                this.customer.getCustomerId()) + " serves by "
                + server.toString() + "\n";
    }
}
