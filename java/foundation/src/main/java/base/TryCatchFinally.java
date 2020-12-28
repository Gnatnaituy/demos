package base;

import java.io.*;

public class TryCatchFinally {

    public static int tryCatchFinally() {

        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream("test.txt"));
             BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream("out.txt"))) {
            int b;
            while ((b = bin.read()) != -1) {
                bout.write(b);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }
    }

    public static void main(String[] args) {
        System.out.println(tryCatchFinally());
    }
}
