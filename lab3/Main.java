import java.util.Scanner;
import java.util.function.Supplier;
import java.lang.IndexOutOfBoundsException;

class Main {
    public static void main(String[] args) throws IndexOutOfBoundsException {
        Scanner sc = new Scanner(System.in);
        ImList<Double> arrivalTimes = new ImList<Double>();
        Supplier<Double> serviceTime = new DefaultServiceTime();

        int numOfServers = sc.nextInt();
        int qmax = sc.nextInt();
        while (sc.hasNextDouble()) {
            arrivalTimes = arrivalTimes.add(sc.nextDouble());
        }

        Simulator sim = new Simulator(numOfServers, qmax, arrivalTimes, serviceTime);
        System.out.println(sim.simulate());
        sc.close();
    }
}
