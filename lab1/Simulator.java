class Simulator {
    private final ImList<Customer> customers;
    private final ImList<Server> servers;

    Simulator(int numOfServers, ImList<Double> arrivalTimes, ImList<Double> serviceTimes) {
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
        int numOfServers = servers.size();

        StringBuilder output = new StringBuilder();
        int served = 0;
        int left = 0;
        ImList<Server> updatedServers = servers;

        for (int i = 0; i < numOfCustomers; i++) {
            for (int j = 0; j < numOfServers; j++) {
                Server currentServer = updatedServers.get(j);
                Customer currentCustomer = currentServer.getCustomer();
                if (!currentServer.checkAvailability() &&
                        (currentCustomer.getArrivalTime() +
                                currentCustomer.getServiceTime() <= customers.get(i)
                                        .getArrivalTime())) {
                    Server finishServing = new Server(j, true, new Customer(0, 0, 0));
                    updatedServers = updatedServers.set(j, finishServing);
                }
            }

            output.append(customers.get(i).toString() + " arrives\n");
            boolean serve = false;

            for (int j = 0; j < numOfServers; j++) {
                if (updatedServers.get(j).checkAvailability()) {
                    output.append(customers.get(i).toString() +
                            " served by " + servers.get(j).toString() + "\n");
                    Server startServing = new Server(j, false, customers.get(i));
                    updatedServers = updatedServers.set(j, startServing);
                    served += 1;
                    serve = true;
                    break;
                }
            }
            if (!serve) {
                output.append(customers.get(i).toString() + " leaves\n");
                left += 1;
            }
        }
        return output.append(String.format("[%d %d]", served, left)).toString();
    }

    @Override
    public String toString() {
        return simulate();
    }
}