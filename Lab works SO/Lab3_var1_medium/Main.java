import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Warehouse {
    private static final int MAX_CAPACITY = 5;
    private List<String> buffer = new ArrayList<>();
    private int breadCount = 0;
    private int butterCount = 0;

    public synchronized void produce(String product) throws InterruptedException {
        while (buffer.size() == MAX_CAPACITY) {
            wait(); // Buffer is full, wait for consumer to consume
        }
        buffer.add(product);
        if (product.equals("Bread")) {
            breadCount++;
        } else if (product.equals("Butter")) {
            butterCount++;
        }
        System.out.println("Produced: " + product);
        printCounts();
        notifyAll(); // Notify waiting consumers
    }

    public synchronized String consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait(); // Buffer is empty, wait for producer to produce
        }
        String product = buffer.remove(0);
        if (product.equals("Bread")) {
            breadCount--;
        } else if (product.equals("Butter")) {
            butterCount--;
        }
        System.out.println("Consumed: " + product);
        printCounts();
        notifyAll(); // Notify waiting producers
        return product;
    }

    private void printCounts() {
        System.out.println("Bread Count: " + breadCount + ", Butter Count: " + butterCount);
    }
}

class Producer implements Runnable {
    private final Warehouse warehouse;
    private final String product;

    public Producer(Warehouse warehouse, String product) {
        this.warehouse = warehouse;
        this.product = product;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(new Random().nextInt(2000)); // Simulate random production time
                warehouse.produce(product);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private final Warehouse warehouse;

    public Consumer(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(new Random().nextInt(3000)); // Simulate random consumption time
                warehouse.consume();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        // Create producers
        Thread breadProducerThread = new Thread(new Producer(warehouse, "Bread"));
        Thread butterProducerThread = new Thread(new Producer(warehouse, "Butter"));

        // Create consumers
        Thread consumer1Thread = new Thread(new Consumer(warehouse));
        Thread consumer2Thread = new Thread(new Consumer(warehouse));

        // Start threads
        breadProducerThread.start();
        butterProducerThread.start();
        consumer1Thread.start();
        consumer2Thread.start();
    }
}