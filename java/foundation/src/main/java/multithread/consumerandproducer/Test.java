package multithread.consumerandproducer;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) throws InterruptedException {

        // 1. Create memory buffer
        Vector sharedQueue = new Vector();
        int size = 4;

        // 2. Create thread pool and thread
        ExecutorService service = Executors.newCachedThreadPool();
        Producer proThread1 = new Producer(sharedQueue, size);
        Producer proThread2 = new Producer(sharedQueue, size);
        Producer proThread3 = new Producer(sharedQueue, size);
        Consumer conThread1 = new Consumer(sharedQueue);
        Consumer conThread2 = new Consumer(sharedQueue);
        Consumer conThread3 = new Consumer(sharedQueue);
        service.execute(proThread1);
        service.execute(proThread2);
        service.execute(proThread3);
        service.execute(conThread1);
        service.execute(conThread2);
        service.execute(conThread3);

        // 3. Sleep a will and try to stop producer (end loop)
        Thread.sleep(10 * 1000);
        proThread1.stop();
        proThread2.stop();
        proThread3.stop();

        // 4. Sleep a little more and then close the thread pool
        Thread.sleep(3000);

        // 5. shutdown() could not interrupt the thread until the task ended
        // The program can't stop because the Consumer keeps running
        service.shutdown();
    }
}
