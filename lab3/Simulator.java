import java.util.function.Supplier;

public class Simulator {
    private final int numOfServers;
    private final int qmax;
    private final ImList<Double> arrivalTimes;
    private final Supplier<Double> serviceTimes;

    public Simulator(int numOfServers,
            int qmax,
            ImList<Double> arrivalTimes,
            Supplier<Double> serviceTimes) {
        this.numOfServers = numOfServers;
        this.qmax = qmax;
        this.arrivalTimes = arrivalTimes;
        this.serviceTimes = serviceTimes;
    }

    private ImList<Server> initializeServers() {
        ImList<Server> serverList = new ImList<>();
        for (int i = 1; i <= numOfServers; i++) {
            Server server = new Server(i, qmax);
            serverList = serverList.add(server);
        }
        return serverList;
    }

    private ImList<Customer> initializeCustomers() {
        ImList<Customer> customerList = new ImList<>();
        for (int i = 1; i <= arrivalTimes.size(); i++) {
            Customer customer = new Customer(i, arrivalTimes.get(i - 1),
                    new DefaultServiceTime());
            customerList = customerList.add(customer);
        }
        return customerList;
    }

    public String simulate() {
        int numOfCustomers = arrivalTimes.size();
        ImList<Server> servers = this.initializeServers();
        ImList<Customer> customers = this.initializeCustomers();

        int served = 0;
        int left = 0;
        double waitingTime = 0.0;

        String output = "";

        PQ<Event> eventPQ = new PQ<>(new EventComp());

        for (int i = 0; i < numOfCustomers; i++) {
            Customer customer = customers.get(i);
            Arrive arrive = new Arrive(customer.getArrivalTime(), customer);
            eventPQ = eventPQ.add(arrive);
        }

        while (!eventPQ.isEmpty()) {
            Pair<Event, PQ<Event>> pair = eventPQ.poll();
            Event e1 = pair.first();
            eventPQ = pair.second();

            output += e1.toString();

            Pair<Event, ImList<Server>> nextEventPair = e1.nextEvent(servers);
            Event nextEvent = nextEventPair.first();
            servers = nextEventPair.second();

            if (e1.isServe()) {
                served += 1;
            } else if (e1.isLeave()) {
                left += 1;
                // wait -> wait (waiting time accumulated)
                // wait -> serve (waiting time accumulated)
            } else if (e1.isWait()) {
                waitingTime += nextEvent.getCurrentTime() - e1.getCurrentTime();
            }

            if (!e1.isTerminal()) {
                if (e1.isWait() && nextEvent.isWait()
                        && (nextEvent.getCurrentTime() - e1.getCurrentTime() < 0.00001)
                        && servers.get(nextEvent.getServerId()-1) {
                    continue;
                } else {
                    eventPQ = eventPQ.add(nextEvent);
                }
            }

        }

        double averageWaitingTime = waitingTime / served;
        output += String.format("[%.3f %d %d]", averageWaitingTime, served, left);
        return output;
    }
}