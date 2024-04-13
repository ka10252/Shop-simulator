import java.util.function.Supplier;

public class Customer {
    private final int customerId;
    private final double arrivalTime;
    private final Supplier<Double> serviceTime;

    // default service time is currently 1.0
    public Customer(int customerId, double arrivalTime, Supplier<Double> serviceTime) {
        this.customerId = customerId;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    public double getServiceTime() {
        return this.serviceTime.get();
    }

    @Override
    public String toString() {
        return String.format("%.3f %d", arrivalTime, customerId);
    }

}
