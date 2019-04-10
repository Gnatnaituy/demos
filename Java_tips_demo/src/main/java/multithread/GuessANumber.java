package multithread;

class GuessANumberThread extends Thread {
    private int number;
    
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
        
        System.out.println("** Correct!" + this.getName() + " in " + count + " guess.**");
    }
}

public class GuessANumber {
    
    public static void main(String[] args) {
        GuessANumberThread t1 = new GuessANumberThread(99);
        t1.run();
    }
}
