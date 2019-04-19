package lanqiao.ninefinal;

import java.util.ArrayList;

public class Two {

    public static void main(String[] args) {
        String string[] = {
                ".........................X............",
                ".......................X.X............",
                ".............XX......XX............XX.",
                "............X...X....XX............XX.",
                ".XX........X.....X...XX...............",
                ".XX........X...X.XX....X.X............",
                "...........X.....X.......X............",
                "............X...X.....................",
                ".............XX.......................",
        };

        int n = 999, z = n / 2;
        boolean[][] b = new boolean[n][n];
        ArrayList<Integer> lie = new ArrayList<Integer>();
        ArrayList<Integer> hang = new ArrayList<Integer>();
        int x = 0;
        for (int i = 0; i < string.length; i++)
            for (int j = 0; j < string[i].length(); j++) {
                if (string[i].charAt(j) == 'X') {
                    x++;
                    lie.add(i + z);
                    hang.add(j + z);
                    b[i + z][j + z] = true;
                }
            }
        System.out.println(x);
        for (int k = 0; k < 1000000000; k++) {
            int y = 0;
            boolean[][] newb = new boolean[n][n];
            ArrayList<Integer> newlie = new ArrayList<>();
            ArrayList<Integer> newhang = new ArrayList<>();
            for (int q = 0; q < lie.size(); q++) {
                int l = lie.get(q), h = hang.get(q);
                int s = 0;
                for (int i = l - 1; i <= l + 1; i++)
                    for (int j = h - 1; j <= h + 1; j++) {
                        if (i == l && j == h) continue;
                        if (b[i][j]) s++;
                        else {
                            int s0 = 0;
                            for (int i0 = i - 1; i0 <= i + 1; i0++)
                                for (int j0 = j - 1; j0 <= j + 1; j0++) {
                                    if (i0 == i && j0 == j) continue;
                                    if (b[i0][j0]) s0++;
                                }
                            if (s0 == 3) {
                                if (!newb[i][j]) {
                                    y++;
                                    newb[i][j] = true;
                                    newlie.add(i);
                                    newhang.add(j);
                                }
                            }
                        }
                    }
                if (s == 2 || s == 3) {
                    y++;
                    newb[l][h] = true;
                    newlie.add(l);
                    newhang.add(h);
                }
            }
            b = newb;
            lie = newlie;
            hang = newhang;
            System.out.print((y - x) + ",");
            x = y;
        }
    }
}
