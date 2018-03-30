import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadSum {
    public static void main(String[] args) {
        final int LIMIT = 10_000_000;
        runTest(new Counter(), LIMIT);
        runTest(new CounterWithLock(), LIMIT);
        runTest(new SynchronousCounter(), LIMIT);
        runTest(new AtomicCounter(), LIMIT);
    }

    public static double runThreads(Counter counter, final int limit) {
        AddTask addTask = new AddTask(counter, limit);
        SubtractTask subtractTask = new SubtractTask(counter, limit);

        Thread thread1 = new Thread(addTask);
        Thread thread2 = new Thread(subtractTask);

        System.out.println("Starting threads");
        long startTime = System.nanoTime();
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Threads interrupted.");
        }

        double elapsed = 1.0e-9 * (System.nanoTime() - startTime);
        System.out.printf("Count 1 to %,d in %.6f sec\n", limit, elapsed);
        System.out.printf("Counter total is %d\n", counter.get());
        return elapsed;
    }

    public static double runMultipleThread(int nThread, Counter counter, final int limit) {
        ExecutorService executorService = Executors.newFixedThreadPool(2 * nThread);
        System.out.println("Starting tasks");
        long startTime = System.nanoTime();
        for (int k = 1; k <= nThread; k++) {
            Runnable addTask = () -> {
                for (int i = 1; i <= limit; i++) {
                    counter.add(i);
                }
            };
            Runnable subTask = () -> {
                for (int i = 1; i <= limit; i++) {
                    counter.add(-i);
                }
            };

            executorService.submit(addTask);
            executorService.submit(subTask);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All down");

        double elapsed = 1.0e-9 * (System.nanoTime() - startTime);
        System.out.printf("Counter total is %d\n", counter.get());
        return elapsed;
    }

    public static void runTest(Counter counter, int limit) {
        System.out.println("Run task using " + counter.getClass().getName());
        double time = runMultipleThread(10, counter, limit);
        System.out.printf("Counting to %,d in %.6fs%n%n", limit, time);
    }
}
