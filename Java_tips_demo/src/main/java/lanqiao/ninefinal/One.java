package lanqiao.ninefinal;

public class One {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        long n = 99999;
        while (true) {
            if (isUnique(Long.toString(n * n).toCharArray())) {
                System.out.println(n * n);
                break;
            } else {
                n--;
            }
        }

        System.out.println(System.currentTimeMillis() - start + "ms");
    }

    private static boolean isUnique(char[] power) {
        boolean[] unique = new boolean[10];

        for (int i = 0; i < 10; i++) {
            if (unique[power[i] - 48]) {
                return false;
            } else {
                unique[power[i] - 48] = true;
            }
        }

        return true;
    }
}
