import java.util.Comparator;
import java.util.function.Supplier;

public class PairComp implements Comparator<Pair<Double, Supplier<Double>>> {

    public int compare(Pair<Double, Supplier<Double>> p1, Pair<Double, Supplier<Double>> p2) {
        if (p1.first() > p2.first()) {
            return 1;
        } else if (p1.first() < p2.first()) {
            return -1;
        } else {
            return 0;
        }
    }
}
