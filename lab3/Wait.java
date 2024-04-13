public class Wait implements Event {
    private final double currentTime;
    private final Customer customer;
    private final Server server;
    private final boolean printed;

    public Wait(double currentTime, Customer customer, Server server, boolean printed) {
        this.currentTime = currentTime;
        this.customer = customer;
        this.server = server;
        this.printed = printed;
    }

    public int getServerId() {
        return this.server.getServerId();
    }

    @Override
    public double getCurrentTime() {
        return this.currentTime;
    }

    @Override
    public Customer getCustomer() {
        return this.customer;
    }

    public boolean isServe() {
        return false;
    }

    public boolean isWait() {
        return true;
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

        if (!currentServer.getQueue().isEmpty()) {
            Customer firstCustomer = currentServer.getQueue().get(0);

            // if this.customer is the first in queue list and server can serve => serve
            if ((currentServer.getAvailableTime() <= this.currentTime) &&
                    (firstCustomer.getCustomerId() == this.customer.getCustomerId())) {
                ImList<Customer> updatedQueue = currentServer.getQueue().remove(0);
                Server updatedServer = new Server(currentServer.getServerId(),
                        currentServer.getAvailableTime() + this.customer.getServiceTime(),
                        customer, currentServer.getQmax(), updatedQueue);
                // update available time of the current
                ImList<Server> updatedServers = servers.set(currentServer.getServerId() - 1,
                        updatedServer);
                Serve serve = new Serve(currentServer.getAvailableTime(),
                        this.customer, updatedServer,
                        updatedServer.getQmax());
                return new Pair<>(serve, updatedServers);

                // if this.customer cannot be served
                // => wait
            } else {
                Wait wait = new Wait(currentServer.getAvailableTime(), customer,
                        currentServer, true);
                return new Pair<>(wait, servers);
            }
        } else {
            Wait wait = new Wait(currentServer.getAvailableTime(), customer, currentServer, true);
            return new Pair<>(wait, servers);
        }

    }

    @Override
    public String toString() {
        if (!this.printed) {
            return String.format(customer.toString() + " waits at " + server.toString() + "\n");
        }
        return "";
    }
}
