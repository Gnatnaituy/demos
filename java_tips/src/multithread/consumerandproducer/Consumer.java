package multithread.consumerandproducer;

import java.util.Random;
import java.util.Vector;

public class Consumer implements Runnable {

    // 公共资源
    private final Vector sharedQueue;

    public Consumer(Vector sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        Random r = new Random();
        System.out.println("Start consumer id = " + Thread.currentThread().getId());
        try {
            while (true) {
                // imitate delay
                Thread.sleep(r.nextInt(3000));

                // block and wait when the Queue is empty
                while (sharedQueue.isEmpty()) {
                    synchronized (sharedQueue) {
                        System.out.println("Queue is empty, consumer " + Thread.currentThread().getId()
                        + " is waiting, size: " + sharedQueue.size());
                        sharedQueue.wait();
                    }
                }
                // consuming element when the queue is not empty
                synchronized (sharedQueue) {
                    System.out.println("consumer consume data: " + sharedQueue.remove(0)
                    + " size: " + sharedQueue.size());
                    sharedQueue.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
