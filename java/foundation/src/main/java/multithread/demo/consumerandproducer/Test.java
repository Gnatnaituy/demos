package multithread.demo.consumerandproducer;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author hasaker
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        Vector<Integer> sharedQueue = new Vector<>();
        ExecutorService service = Executors.newCachedThreadPool();

        Producer producerThread1 = new Producer(sharedQueue, 10);
        Producer producerThread2 = new Producer(sharedQueue, 20);
        Producer producerThread3 = new Producer(sharedQueue, 30);
        Consumer consumerThread1 = new Consumer(sharedQueue);
        Consumer consumerThread2 = new Consumer(sharedQueue);
        Consumer consumerThread3 = new Consumer(sharedQueue);
        service.execute(producerThread1);
        service.execute(producerThread2);
        service.execute(producerThread3);
        service.execute(consumerThread1);
        service.execute(consumerThread2);
        service.execute(consumerThread3);

        Thread.sleep(1000);
        producerThread1.stop();
        producerThread2.stop();
        producerThread3.stop();

        Thread.sleep(1000);
        service.shutdown();
    }
}
