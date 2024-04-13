class Server {
    private final int serverId;
    private final double availableTime;
    private final Customer customer;

    Server(int serverId) {
        this.serverId = serverId;
        this.availableTime = 0;
        this.customer = new Customer(0, 0, 0);
    }

    Server(int serverId, double availableTime, Customer customer) {
        this.serverId = serverId;
        this.availableTime = availableTime;
        this.customer = customer;
    }

    int getId() {
        return serverId;
    }

    boolean checkAvailability(Customer customer) {
        if (this.availableTime <= customer.getArrivalTime()) {
            return true;
        }
        return false;
    }

    Customer getCustomer() {
        return this.customer;
    }

    double getAvailableTime() {
        return this.availableTime;
    }

    @Override
    public String toString() {
        return String.format("%d", serverId);
    }
}
