package multithread.demo;

public class GuessANumber {
    
    public static void main(String[] args) {
        GuessANumberThread t1 = new GuessANumberThread(99);
        GuessANumberThread t2 = new GuessANumberThread(98);
        t1.start();
        t2.start();
    }
}

class GuessANumberThread extends Thread {
    private final int number;

    GuessANumberThread(int number) {
        this.number = number;
    }

    public void run() {
        int count = 0;
        int guess;

        do {
            guess = (int) (Math.random() * 100 + 1);
            System.out.println(this.getName() + " guess " + guess);
            count++;
        } while (guess != number);

        System.out.println("Correct! " + this.getName() + " in " + count + " guess.");
    }
}
