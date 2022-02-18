package multithread.fundation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SleepVsNotify {

    @AllArgsConstructor
    static class SleepThread extends Thread {
        private final String name;
        private final long timeout;
        private final Thread target;

        @Override
        public void run() {
            log.info("{} started...", name);
            try {
                log.info("{} sleep {}ms start...", name, timeout);
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                log.error("{} interrupted in sleep", name);
                return;
            }

            if (target != null) {
                target.interrupt();
            }

            log.info("{} finished", name);
        }
    }

    @AllArgsConstructor
    static class WaitThread extends Thread {
        private final String name;
        private final long sleepTimeout;
        private final long waitTimeout;
        private final Thread target;
        private final String lock;

        @Override
        public void run() {
            log.info("{} started...", name);
            synchronized (lock) {
                try {
                    log.info("{} get lock on {}, start sleep for {}ms...", name, lock, sleepTimeout);
                    Thread.sleep(sleepTimeout);
                    log.info("{} wakeup, start wait for {}ms", name, waitTimeout);
                    lock.wait(waitTimeout);
                } catch (InterruptedException e) {
                    log.error("{} interrupted while waiting", name);
                    return;
                }

                if (target != null) {
                    target.interrupt();
                }

                log.info("{} finished", name);
            }
        }
    }

    public void testSleep() {
        Thread thread1 = new SleepThread("Thread-1", 10000, null);
        Thread thread2 = new SleepThread("Thread-2", 5000, thread1);

        thread1.start();
        thread2.start();
    }

    public void testWait() {
        String lock1 = "Lock-1";
        String lock2 = "Lock-2";
        String lock3 = "Lock-3";

        WaitThread thread1 = new WaitThread("WaitThread-1", 10000, 10000, null, lock1);
        WaitThread thread2 = new WaitThread("WaitThread-2", 5000, 5000, thread1, lock1);
        WaitThread thread3 = new WaitThread("WaitThread-3", 20000, 20000,null, lock3);

        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static void main(String[] args) {
        SleepVsNotify sleepVsNotify = new SleepVsNotify();
        sleepVsNotify.testSleep();
        sleepVsNotify.testWait();
    }
}
