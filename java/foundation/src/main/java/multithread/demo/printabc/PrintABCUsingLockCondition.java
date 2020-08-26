package multithread.demo.printabc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABCUsingLockCondition {
    private int times;
    private int state;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    private PrintABCUsingLockCondition(int times) {
        this.times = times;
    }

    public static void main(String[] args) {
        PrintABCUsingLockCondition printABC = new PrintABCUsingLockCondition(10);
        new Thread(printABC::printA).start();
        new Thread(printABC::printB).start();
        new Thread(printABC::printC).start();
    }

    private void printA() {
        print("A", 0, conditionA, conditionB);
    }

    private void printB() {
        print("B", 1, conditionB, conditionC);
    }

    private void printC() {
        print("C", 2, conditionC, conditionA);
    }

    private void print(String name, int targetState, Condition current, Condition next) {
        for (int i = 0; i < times;) {
            lock.lock();
            try {
                while (state % 3 != targetState) {
                    current.await();
                }
                state++;
                i++;
                System.out.print(name);
                next.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
