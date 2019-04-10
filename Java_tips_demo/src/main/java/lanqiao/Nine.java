package lanqiao;

import java.time.LocalDate;

public class Nine {

    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDate end = LocalDate.of(2000, 5, 4);
        System.out.println(end.toEpochDay() - start.toEpochDay());
        O_n();

        System.out.println(win(10));
        System.out.println(win(1));
        System.out.println(win(4));
    }

    private static void O_n() {
        int x = 1000;
        int y = 1000;
        int sum = 0;
        int b = 0;
        int tempB = 0;
        for (int a = x; a > 0; a--) {
            while (a * a + b * b <= x * y) {
                b++;
            }
            b--;
            sum += (b - tempB) * a;
            tempB = b;
        }
        System.out.println(sum * 4);
    }

    private static boolean win(int n) {
        if (n >= 8 && !win(n - 8)) return true;
        if (n >= 7 && !win(n - 7)) return true;
        if (n >= 3 && !win(n - 3)) return true;
        if (n >= 1 && !win(n - 1)) return true;

        return false;
    }
}
