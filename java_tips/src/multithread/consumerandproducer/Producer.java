package multithread.consumerandproducer;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

    // true --> producer keep producing
    // false --> producer stop producing
    private volatile boolean isRunning = true;

    // public resource
    private final Vector sharedQueue;

    // the max size of the public resource
    private final int SIZE;

    // producing data
    private static AtomicInteger count = new AtomicInteger();

    public Producer(Vector sharedQueue, int SIZE) {
        this.sharedQueue = sharedQueue;
        this.SIZE = SIZE;
    }

    @Override
    public void run() {
        int data;
        Random r = new Random();

        System.out.println("start produce id " + Thread.currentThread().getId());
        try {
            while (isRunning) {
                // imitate delay
                Thread.sleep(r.nextInt(3000));

                // block and wait when the queue is full
                while (sharedQueue.size() == SIZE) {
                    synchronized (sharedQueue) {
                        System.out.println("queue is full, producer " + Thread.currentThread().getId()
                        + " is waiting, size: " + sharedQueue.size());
                    }
                }

                // producing new element when the queue is not full
                synchronized (sharedQueue) {
                    data = count.incrementAndGet();
                    sharedQueue.add(data);
                    System.out.println("producer create data: " + data + ", size: " + sharedQueue.size());
                    sharedQueue.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        isRunning = false;
    }
}
