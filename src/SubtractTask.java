public class SubtractTask implements Runnable {
    private Counter counter;
    private int limit;

    public SubtractTask(Counter counter, int limit) {
        this.counter = counter;
        this.limit = limit;
    }

    @Override
    public void run() {
        for (int k = 1; k <= limit; k++) {
            counter.add(-k);
        }
        System.out.println("Done " + Thread.currentThread().getName());
    }
}
