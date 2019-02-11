package multithread;

class MyRunnable implements Runnable {
    private Thread t;
    private String threadName;
    
    MyRunnable(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }
    
    public void run() {
        System.out.println("Running " + threadName);
        try {
            for (int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thead " + threadName + " interrupted.");
        }
        System.out.println("Thread " + threadName + " exiting.");
    }
    
    void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}


public class TestRunnable {
    
    public static void main(String[] args) {
        MyRunnable r1 = new MyRunnable("Thread-1");
        r1.start();
        
        MyRunnable r2 = new MyRunnable("Thread-2");
        r2.start();
    }
}


