import java.util.Comparator;

class EventComp implements Comparator<Event> {
    
    @Override
    public int compare(Event e1, Event e2) {
        if (e1.getCurrentTime() > e2.getCurrentTime()) {
            return  1;
        } else if (e1.getCurrentTime() < e2.getCurrentTime()) {
            return -1;
        } else {
            int customerId1 = e1.getCustomer().getCustomerId();  
            int customerId2 = e2.getCustomer().getCustomerId();
            return Integer.compare(customerId1, customerId2);
        }
    }
}