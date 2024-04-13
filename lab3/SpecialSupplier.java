import java.util.function.Supplier;

public class SpecialSupplier<E> implements Supplier<E> {
    private final E data;

    SpecialSupplier(E data) {
        this.data = data;
    }

    public E get() {
        return data;
    }
}