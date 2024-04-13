class Arrive implements Event {
    private final double currentTime;
    private final Customer customer;

    public Arrive(double currentTime, Customer customer) {
        this.currentTime = currentTime;
        this.customer = customer;
    }

    @Override
    public double getCurrentTime() {
        return this.currentTime;
    }

    @Override
    public String type() {
        return "arrive";
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public Pair<Event, ImList<Server>> nextEvent(ImList<Server> servers) {
        int numOfServers = servers.size();
        for (int i = 1; i <= numOfServers; i++) {
            Server currentServer = servers.get(i - 1);
            if (currentServer.checkAvailability(customer)) {
                Serve serve = new Serve(this.currentTime, this.customer, currentServer);
                servers = servers.set(i - 1,
                        new Server(i,
                                customer.getArrivalTime() + customer.getServiceTime(), customer));
                return new Pair<>(serve, servers);
            }
        }
        Leave leave = new Leave(this.currentTime, this.customer);
        return new Pair<>(leave, servers);
    }

    @Override
    public String toString() {
        return customer.toString() + " arrives";
    }
}
