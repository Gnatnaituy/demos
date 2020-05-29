package multithread;

import java.util.concurrent.Semaphore;

/**
 * @auther hasaker
 * @create_date 2019-08-09 10:32
 * @description
 */
public class CustomerCheckWindow {

    private static final int CUSTOMER_COUNT = 15;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);

        for (int i = 1; i < CUSTOMER_COUNT; i++) {
            new SecurityCheckThread(i, semaphore).start();
        }
    }

    private static class SecurityCheckThread extends Thread {
        private final int seq;
        private final Semaphore semaphore;

        SecurityCheckThread(int seq, Semaphore semaphore) {
            this.seq = seq;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("No." + seq + " 旅客正在接受检查...");

                if (seq % 2 == 0) {
                    Thread.sleep(5000);
                    System.out.println("No." + seq + " 旅客身份可疑, 被禁止出国!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
                System.out.println("No." + seq + " 旅客检查完毕.");
            }
        }
    }
}
