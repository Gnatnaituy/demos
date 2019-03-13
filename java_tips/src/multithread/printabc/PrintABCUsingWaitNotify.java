package multithread.printabc;

public class PrintABCUsingWaitNotify {
    private int times;
    private int state;
    private Object objectA = new Object();
    private Object objectB = new Object();
    private Object objectC = new Object();

    private PrintABCUsingWaitNotify(int times) {
        this.times = times;
    }

    public static void main(String[] args) {
        PrintABCUsingWaitNotify printABC = new PrintABCUsingWaitNotify(10);
        new Thread(printABC::printA).start();
        new Thread(printABC::printB).start();
        new Thread(printABC::printC).start();
    }

    private void printA() {
        try {
            print("A", 0, objectA, objectB);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printB() {
        try {
            print("B", 1, objectB, objectC);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printC() {
        try {
            print("C", 2, objectC, objectA);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void print(String name, int targetState, Object current, Object next)
            throws InterruptedException {
        for (int i = 0; i < times;) {
            synchronized (current) {
                while (state % 3 != targetState) {
                    current.wait();
                }
                state++;
                i++;
                System.out.print(name);
                synchronized (next) {
                    next.notify();
                }
            }
        }
    }

}
