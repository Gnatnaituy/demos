package multithread.demo.consumerandproducer;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

    private volatile boolean isRunning = true;
    private final Vector<Integer> sharedQueue;
    private final int MAX_QUEUE_SIZE;
    private static final AtomicInteger count = new AtomicInteger();

    Producer(Vector<Integer> sharedQueue, int MAX_QUEUE_SIZE) {
        this.sharedQueue = sharedQueue;
        this.MAX_QUEUE_SIZE = MAX_QUEUE_SIZE;
    }

    @Override
    public void run() {
        int data;
        System.out.println("start produce id " + Thread.currentThread().getId());

        while (isRunning) {
            while (sharedQueue.size() >= MAX_QUEUE_SIZE) {
                synchronized (sharedQueue) {
                    System.out.println("queue is full, producer " + Thread.currentThread().getId() + " is waiting, size: " + sharedQueue.size());
                }
            }

            synchronized (sharedQueue) {
                data = count.incrementAndGet();
                sharedQueue.add(data);
                System.out.println("producer-" + Thread.currentThread().getId() + " create data: " + data + ", size: " + sharedQueue.size());
                sharedQueue.notifyAll();
            }
        }
    }

    void stop() {
        isRunning = false;
    }
}
