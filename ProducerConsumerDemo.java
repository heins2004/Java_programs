import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class Producer implements Runnable {

    private BlockingQueue<Integer> queue;
    private AtomicInteger producedCount;
    private static AtomicInteger idGenerator = new AtomicInteger(1);

    public Producer(BlockingQueue<Integer> queue,
                    AtomicInteger producedCount) {

        this.queue = queue;
        this.producedCount = producedCount;
    }

    @Override
    public void run() {

        Random rand = new Random();

        try {
            while (true) {

                int item = idGenerator.getAndIncrement();

                queue.put(item);

                producedCount.incrementAndGet();

                System.out.println(
                        Thread.currentThread().getName() +
                        " PRODUCED -> " + item +
                        " | Queue: " + queue
                );

                Thread.sleep(rand.nextInt(1000) + 500);
            }

        } catch (InterruptedException e) {

            System.out.println(
                    Thread.currentThread().getName() +
                    " stopped."
            );
        }
    }
}

class Consumer implements Runnable {

    private BlockingQueue<Integer> queue;
    private AtomicInteger consumedCount;

    public Consumer(BlockingQueue<Integer> queue,
                    AtomicInteger consumedCount) {

        this.queue = queue;
        this.consumedCount = consumedCount;
    }

    @Override
    public void run() {

        Random rand = new Random();

        try {
            while (true) {

                int item = queue.take();

                consumedCount.incrementAndGet();

                System.out.println(
                        Thread.currentThread().getName() +
                        " CONSUMED -> " + item +
                        " | Queue: " + queue
                );

                Thread.sleep(rand.nextInt(1500) + 700);
            }

        } catch (InterruptedException e) {

            System.out.println(
                    Thread.currentThread().getName() +
                    " stopped."
            );
        }
    }
}

public class ProducerConsumerDemo {

    public static void main(String[] args)
            throws InterruptedException {

        BlockingQueue<Integer> queue =
                new ArrayBlockingQueue<>(5);

        AtomicInteger producedCount =
                new AtomicInteger(0);

        AtomicInteger consumedCount =
                new AtomicInteger(0);

        ExecutorService executor =
                Executors.newFixedThreadPool(4);

        // Start Producers
        executor.execute(
                new Producer(queue, producedCount)
        );

        executor.execute(
                new Producer(queue, producedCount)
        );

        // Start Consumers
        executor.execute(
                new Consumer(queue, consumedCount)
        );

        executor.execute(
                new Consumer(queue, consumedCount)
        );

        // Run system
        Thread.sleep(15000);

        // Shutdown
        executor.shutdownNow();

        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("\n=== SYSTEM REPORT ===");
        System.out.println(
                "Total Produced: " +
                producedCount.get()
        );

        System.out.println(
                "Total Consumed: " +
                consumedCount.get()
        );

        System.out.println("System shutdown complete.");
    }
}
