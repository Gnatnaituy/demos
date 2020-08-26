package multithread.demo;


public class PrintOddEvenNumber {
    private static int cnt = 1;
    
    public static void main(String[] args) {
        EvenOddNumberThread myThread = new EvenOddNumberThread();
        
        Thread t1 = new Thread(myThread);
        Thread t2 = new Thread(myThread);
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private static class EvenOddNumberThread extends Thread {
        
        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    this.notify();
                    if (cnt <= 1000) {
                        String currentThreadName = Thread.currentThread().getName();
                        System.err.println(currentThreadName + " : " + cnt++);
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }
}

