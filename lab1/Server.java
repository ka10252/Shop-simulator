class Server {
    private final int serverId;
    private final boolean availability;
    private final Customer customer;

    Server(int serverId) {
        this.serverId = serverId;
        this.availability = true;
        this.customer = new Customer(0, 0, 0);
    }

    Server(int serverId, boolean availability, Customer customer) {
        this.serverId = serverId;
        this.availability = availability;
        this.customer = customer;
    }

    int getServerId() {
        return this.serverId;
    }

    boolean checkAvailability() {
        return this.availability;
    }

    Customer getCustomer() {
        return this.customer;
    }

    @Override
    public String toString() {
        return String.format("server %d", serverId);
    }
}
