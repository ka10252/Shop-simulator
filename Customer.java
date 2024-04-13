class Customer {
    private final int customerId;
    private final double arrivalTime;
    private final double serviceTime;

    Customer(int customerId, double arrivalTime, double serviceTime) {
        this.customerId = customerId;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    int getCustomerId() {
        return this.customerId;
    }

    double getArrivalTime() {
        return this.arrivalTime;
    }

    double getServiceTime() {
        return this.serviceTime;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d", arrivalTime, customerId);
    }

    public String toStringAft() {
        return String.format("%.3f %d", arrivalTime + serviceTime, customerId);
    }
}
