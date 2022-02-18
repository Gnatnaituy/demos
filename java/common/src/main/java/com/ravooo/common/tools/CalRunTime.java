package com.ravooo.common.tools;

public class CalRunTime {

    public static void calRunTime(AsyncExec func) {
        long begin = System.currentTimeMillis();
        func.exec();
        System.out.println("function runtime: " + (System.currentTimeMillis() - begin) + "ms");
    }

    @FunctionalInterface
    public interface AsyncExec {
        void exec();
    }
}
