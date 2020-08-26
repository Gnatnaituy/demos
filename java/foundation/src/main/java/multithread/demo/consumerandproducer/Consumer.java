package multithread.demo.consumerandproducer;

import java.util.Vector;

public class Consumer implements Runnable {

    private final Vector<Integer> sharedQueue;

    Consumer(Vector<Integer> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        System.out.println("Start consumer id = " + Thread.currentThread().getId());
        while (sharedQueue.isEmpty()) {
            synchronized (sharedQueue) {
                System.out.println("Queue is empty, consumer " + Thread.currentThread().getId() + " is waiting, size: " + sharedQueue.size());
                try {
                    sharedQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        synchronized (sharedQueue) {
            System.out.println("consumer-" + Thread.currentThread().getId() + " consume data: " + sharedQueue.remove(0) + " size: " + sharedQueue.size());
            sharedQueue.notifyAll();
        }
    }
}
