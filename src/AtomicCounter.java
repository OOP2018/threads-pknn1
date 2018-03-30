import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter extends Counter {
    private AtomicLong total;

    public AtomicCounter() {
        total = new AtomicLong();
    }

    @Override
    public void add(int amount) {
        total.getAndAdd(amount);
    }

    @Override
    public long get() {
        return total.get();
    }
}
