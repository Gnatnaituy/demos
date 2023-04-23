package base;

public class ForLoopDemo {

    public static void loopOutside() {
        String a = null;
        for (int i = 0; i < 100; i++) {
            a = String.valueOf(i);
        }
    }

    public static void loopInside() {
        for (int i = 0; i < 100; i++) {
            String a = String.valueOf(i);
        }
    }
}
