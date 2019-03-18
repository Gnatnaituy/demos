package multithread;

public class TestJoin {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " start running");
        Thread thread1 = new Thread(new JoinThread("A"));
        Thread thread2 = new Thread(new JoinThread("B"));
        thread1.start();
        thread2.start();
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MIN_PRIORITY);

        /*
        join()是Thread类的一个方法，启动线程后直接调用，
        即join()的作用是：“等待该线程终止”，这里需要理解的就是该线程是指的主线程等待子线程的终止。
        也就是在子线程调用了join()方法后面的代码，只有等到子线程结束了才能执行

        为什么要用join()方法?
        在很多情况下，主线程生成并起动了子线程，如果子线程里要进行大量的耗时的运算，主线程往往将于子线程之前结束，
        但是如果主线程处理完其他的事务后，需要用到子线程的处理结果，也就是主线程需要等待子线程执行完成之后再结束，
        这个时候就要用到join()方法了
         */
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " stop running");
    }
}


class JoinThread implements Runnable {
    private String name;

    JoinThread(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " start running...");
        for (int i = 0; i < 5; i++) {
            System.out.println("Sub thread " + name + " running: " + i);
            try {
                Thread.sleep((int) (Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " stop running...");
    }
}
