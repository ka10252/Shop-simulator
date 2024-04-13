public class Server {
    private final int serverId;
    private final double availableTime;
    private final Customer customer;
    private final int qmax;
    private final ImList<Customer> queue;

    public Server(int serverId, int qmax) {
        this.serverId = serverId;
        this.availableTime = 0;
        this.customer = new Customer(0, 0, new DefaultServiceTime());
        this.qmax = qmax;
        this.queue = new ImList<Customer>();
    }

    public Server(int serverId, double availableTime,
            Customer customer, int qmax, ImList<Customer> queue) {
        this.serverId = serverId;
        this.availableTime = availableTime;
        this.customer = customer;
        this.qmax = qmax;
        this.queue = queue;
    }

    public Server(int serverId, double availableTime, Customer customer, int qmax) {
        this.serverId = serverId;
        this.availableTime = availableTime;
        this.customer = customer;
        this.qmax = qmax;
        this.queue = new ImList<Customer>();
    }

    public int getServerId() {
        return serverId;
    }

    public double getAvailableTime() {
        return this.availableTime;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public int getQmax() {
        return qmax;
    }

    public ImList<Customer> getQueue() {
        return this.queue;
    }

    public int getQueuesize() {
        return this.queue.size();
    }

    public boolean queueAvailability() {
        if (this.queue.size() < this.qmax) {
            return true;
        }
        return false;
    }

    public boolean serveAvailability(Customer customer) {
        if (this.availableTime <= customer.getArrivalTime()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%d", serverId);
    }
}
