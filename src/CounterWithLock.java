import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterWithLock extends Counter {
    private Lock lock = new ReentrantLock();
    @Override
    public void add(int amount) {
        try {
            lock.lock();
            super.add(amount);
        } finally {
            lock.unlock();
        }
    }
}
