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
        return true;
    }

    public boolean isDone() {
        return false;
    }

    public boolean isTerminal() {
        return false;
    }

    @Override
    public Pair<Event, ImList<Server>> nextEvent(ImList<Server> servers) {
        int numOfServers = servers.size();

        // loop through server list to check whether customers can be served
        for (int i = 1; i <= numOfServers; i++) {
            Server currentServer = servers.get(i - 1);
            int queueSize = currentServer.getQueuesize();

            // if no customers in queue list and server can serve => serve
            if ((queueSize == 0) && currentServer.serveAvailability(customer)) {
                Serve serve = new Serve(customer.getArrivalTime(), this.customer,
                        currentServer, currentServer.getQmax());
                Server updatedServer = new Server(i,
                        customer.getArrivalTime() + customer.getServiceTime(),
                        customer, currentServer.getQmax());
                ImList<Server> updatedServers = servers.set(i - 1, updatedServer);
                return new Pair<>(serve, updatedServers);
            }
        }

        for (int i = 1; i <= numOfServers; i++) {
            Server currentServer = servers.get(i - 1);

            // if queue list is not full and server cannot serve => wait
            if (!currentServer.serveAvailability(customer) &&
                    currentServer.queueAvailability()) {

                // increase queueSize if the customer is added to a queue
                ImList<Customer> updatedQueue = currentServer.getQueue().add(customer);
                Server updatedServer = new Server(i,
                        currentServer.getAvailableTime(), customer,
                        currentServer.getQmax(), updatedQueue);
                Wait wait = new Wait(customer.getArrivalTime(), this.customer,
                        updatedServer, false);
                ImList<Server> updatedServers = servers.set(i - 1, updatedServer);
                return new Pair<>(wait, updatedServers);
            }
        }
        // if customer cannot be served right away or cannot wait in queue => leave
        Leave leave = new Leave(this.currentTime, this.customer);
        return new Pair<>(leave, servers);
    }

    @Override
    public String toString() {
        return customer.toString() + " arrives\n";
    }
}
