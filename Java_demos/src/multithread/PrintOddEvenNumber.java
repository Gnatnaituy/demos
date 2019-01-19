package src.multithread;

public class PrintOddEvenNumber {
    private static int cnt = 1;
    
    public static void main(String[] args) {
        EvenOddNumberThead myThread = new EvenOddNumberThead();
        
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
    
    private static class EvenOddNumberThead extends Thread {
        
        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    this.notify();
                    if (cnt <= 100000000) {
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

