package multithread;

import java.util.concurrent.*;

/**
 * @auther hasaker
 * @create_date 2019-08-08 10:00
 * @description Mocking dead lock of thread
 */
public class DeadLockDemo {

    private static final Object RESOURCE1 = new Object();
    private static final Object RESOURCE2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (RESOURCE1) {
                System.out.println(Thread.currentThread() + " get resource 1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread() + " waiting get resource2");
                synchronized (RESOURCE2) {
                    System.out.println(Thread.currentThread() + " get resource2");
                }
            }
        }, "线程-1").start();

        new Thread(() -> {
            synchronized (RESOURCE2) {
                System.out.println(Thread.currentThread() + " get resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread() + " waiting get resource1");
                synchronized (RESOURCE1) {
                    System.out.println(Thread.currentThread() + " get resource1");
                }
            }
        }, "线程-2").start();
    }
}
