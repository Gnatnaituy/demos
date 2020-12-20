package main.java.tools;

public class CalRunTime {

    public static void calRunTime(AsyncExec func) {
        long begin = System.currentTimeMillis();
        func.exec();
        System.out.println("Runtime: " + (System.currentTimeMillis() - begin) + "ms");
    }

    @FunctionalInterface
    public interface AsyncExec {
        void exec();
    }
}
