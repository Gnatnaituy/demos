package multithread.fundation;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class RunnableDemo {

    public static void main(String[] args) {
        MyRunnable r1 = new MyRunnable("Thread-1");
        MyRunnable r2 = new MyRunnable("Thread-2");
        MyRunnable r3 = new MyRunnable("Thread-3");
        r2.start();
        r1.start();
        r3.start();
    }

    static class MyRunnable implements Runnable {
        private Thread t;
        private final String threadName;

        MyRunnable(String name) {
            threadName = name;
            System.out.println("Creating " + threadName);
        }

        public void start() {
            System.out.println("Starting " + threadName);
            if (t == null) {
                t = new Thread(this, threadName);
                t.start();
            }
        }

        public void run() {
            System.out.println("Running " + threadName);
            try {
                for (int i = 5; i > 0; i--) {
                    System.out.println(threadName + ", " + i);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted.");
            }
            System.out.println(threadName + " exiting.");
        }

        AbstractQueuedSynchronizer
    }
}
