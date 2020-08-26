package multithread.fundation;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @Project java
 * @Author Ravooo
 * @Since 2020/8/27 00:25
 * @Description Piped
 */
public class Piped {

    public static void main(String[] args) throws Exception {
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();
        writer.connect(reader);

        Thread printThread = new Thread(new Print(reader), "PrintThread");
        printThread.start();

        int receive;
        try {
            while ((receive = System.in.read()) != -1) {
                writer.write(receive);
            }
        } finally {
            writer.close();
        }
    }

    static class Print implements Runnable {
        private final PipedReader reader;

        public Print(PipedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            int receive;
            try {
                while ((receive = reader.read()) != -1) {
                    System.out.print((char) receive);
                }
            } catch (IOException e) {
                System.out.println(Thread.currentThread() + " interrupted");
            }
        }
    }
}
