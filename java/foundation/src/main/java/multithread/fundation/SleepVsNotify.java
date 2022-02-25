package multithread.fundation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SleepVsNotify {

    @AllArgsConstructor
    static class SleepWaitThread extends Thread {
        private final String name;
        private final long sleepSec;
        private final long waitSec;
        private final Thread target;
        private final String lock;

        @Override
        public void run() {
            log.info("{} started...", name);
            synchronized (lock) {
                try {
                    log.info("{} get lock on {}, start sleep for {}s...", name, lock, sleepSec);
                    Thread.sleep(sleepSec * 1000);
                    log.info("{} wakeup, start wait for {}s", name, waitSec);
                    lock.wait(waitSec * 1000);
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

    public void testSleepWait() {
        String lock1 = "Lock-1";
        String lock2 = "Lock-2";

        SleepWaitThread thread1 = new SleepWaitThread("SleepWaitThread-1", 5, 5, null, lock1);
        SleepWaitThread thread2 = new SleepWaitThread("SleepWaitThread-2", 2, 2, thread1, lock2);
        SleepWaitThread thread3 = new SleepWaitThread("SleepWaitThread-3", 5, 5,null, lock2);

        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static void main(String[] args) {
        SleepVsNotify sleepVsNotify = new SleepVsNotify();
        sleepVsNotify.testSleepWait();
    }
}
