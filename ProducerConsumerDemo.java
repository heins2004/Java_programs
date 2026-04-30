import java.util.LinkedList;
import java.util.Queue;

class SharedBuffer {
    private Queue<Integer> buffer = new LinkedList<>();
    private int capacity;

    public SharedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int value) throws InterruptedException {
        while (buffer.size() == capacity) {
            wait(); // wait if buffer full
        }

        buffer.add(value);
        System.out.println("Produced: " + value);

        notify(); // notify consumer
    }

    public synchronized void consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait(); // wait if buffer empty
        }

        int value = buffer.poll();
        System.out.println("Consumed: " + value);

        notify(); // notify producer
    }
}

class Producer extends Thread {
    private SharedBuffer buffer;

    public Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        int i = 1;
        try {
            while (true) {
                buffer.produce(i++);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Producer stopped.");
        }
    }
}

class Consumer extends Thread {
    private SharedBuffer buffer;

    public Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            while (true) {
                buffer.consume();
                Thread.sleep(800);
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer stopped.");
        }
    }
}

public class ProducerConsumerDemo {

    public static void main(String[] args) {

        SharedBuffer buffer = new SharedBuffer(5);

        Producer p = new Producer(buffer);
        Consumer c = new Consumer(buffer);

        p.start();
        c.start();
    }
}