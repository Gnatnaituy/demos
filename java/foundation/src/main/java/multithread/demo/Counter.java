package multithread.demo;

import java.util.stream.IntStream;

import com.ravooo.common.tools.CalRunTime;

import lombok.Getter;

public class Counter {

    @Getter
    private static int counter = 0;

    private static final Object locker = new Object();

    public static void reset() {
        counter = 0;
    }

    /**
     * 在非静态的 wrong 方法上加锁，只能确保多个线程无法执行同一个实例的 wrong 方法，却不能保证不会执行不同实例的 wrong 方法。
     * 而静态的 counter 在多个实例中共享，所以必然会出现线程安全问题。
     */
    public synchronized void wrong() {
        counter++;
    }

    public void right() {
        synchronized (locker) {
            counter++;
        }
    }

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        Counter.reset();
        IntStream.rangeClosed(1, 100000000).parallel().forEach(o -> new Counter().wrong());
        System.out.println(Counter.getCounter() + " " + (System.currentTimeMillis() - begin) + "ms");

        begin = System.currentTimeMillis();
        Counter.reset();
        IntStream.rangeClosed(1, 100000000).parallel().forEach(o -> new Counter().right());
        System.out.println(Counter.getCounter() + " " + (System.currentTimeMillis() - begin) + "ms");
        CalRunTime.calRunTime(System.out::println);

        CalRunTime.calRunTime(() -> {
            Counter.reset();
            IntStream.rangeClosed(1, 100000000).parallel().forEach(o -> new Counter().wrong());
        });

        CalRunTime.calRunTime(() -> {
            Counter.reset();
            IntStream.rangeClosed(1, 100000000).parallel().forEach(o -> new Counter().right());
        });
    }
}
