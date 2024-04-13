public class Simulator {
    private final ImList<Customer> customers;
    private final ImList<Server> servers;

    public Simulator(int numOfServers, ImList<Double> arrivalTimes, ImList<Double> serviceTimes) {
        ImList<Customer> customerlist = new ImList<>();
        ImList<Server> serverlist = new ImList<>();

        for (int i = 0; i < numOfServers; i++) {
            serverlist = serverlist.add(new Server(i + 1));
        }

        int numOfCustomers = arrivalTimes.size();
        for (int i = 0; i < numOfCustomers; i++) {
            customerlist = customerlist.add(new Customer(i + 1,
                    arrivalTimes.get(i), serviceTimes.get(i)));
        }

        this.customers = customerlist;
        this.servers = serverlist;
    }

    public String simulate() {
        int numOfCustomers = customers.size();
        ImList<Server> servers = this.servers;
        int served = 0;
        int left = 0;
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

            output += e1.toString() + "\n";

            Pair<Event, ImList<Server>> nextEventPair = e1.nextEvent(servers);
            Event nextEvent = nextEventPair.first();
            servers = nextEventPair.second();

            if (e1.type().equals("serve")) {
                served += 1;
            } else if (e1.type().equals("leave")) {
                left += 1;
                continue;
            }

            if (nextEvent != e1) {
                eventPQ = eventPQ.add(nextEvent);
            }

        }
        output += String.format("[%d %d]", served, left);
        return output;
    }
}