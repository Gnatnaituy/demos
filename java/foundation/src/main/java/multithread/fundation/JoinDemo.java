package multithread.fundation;

public class JoinDemo {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " start running");


        System.out.println("123+12312+11".replaceAll("\\+", ""));

        Thread thread1 = new Thread(new JoinThread("A"));
        Thread thread2 = new Thread(new JoinThread("B"));
        thread1.start();
        thread2.start();
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MIN_PRIORITY);

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

    static class JoinThread implements Runnable {
        private final String name;

        JoinThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start running...");

            for (int i = 0; i < 5; i++) {
                System.out.println("Subthread-" + name + " running: " + i);
                try {
                    Thread.sleep((int) (Math.random() * 10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() + " stop running...");
        }
    }
}
