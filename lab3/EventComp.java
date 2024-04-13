import java.util.Comparator;

class EventComp implements Comparator<Event> {

    @Override
    public int compare(Event e1, Event e2) {
        if (e1.getCurrentTime() > e2.getCurrentTime()) {
            return 1;
        } else if (e1.getCurrentTime() < e2.getCurrentTime()) {
            return -1;
            // when the event is occurring at the same time
        } else {
            int customerId1 = e1.getCustomer().getCustomerId();
            int customerId2 = e2.getCustomer().getCustomerId();

            // run smaller customerId first
            if (customerId1 > customerId2) {
                return 1;

                // compare event priority if the customerId is the same
            } else if (customerId1 == customerId2) {
                if (e1.isArrive() && e2.isServe()) {
                    return -1;
                } else if (e1.isArrive() && e2.isWait()) {
                    return -1;
                } else if (e1.isArrive() && e2.isLeave()) {
                    return -1;
                } else if (e1.isArrive() && e2.isDone()) {
                    return -1;
                } else if (e1.isWait() && e2.isServe()) {
                    return -1;
                } else if (e1.isDone() && e2.isWait()) {
                    return -1;
                }
            } else {
                return -1;
            }
            return 1;
        }
    }
}