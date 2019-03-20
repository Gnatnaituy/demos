package lanqiao;

import java.util.Vector;

public class Six {

    /*
    1. 28
     */

    /**
     * 2. 立方变自身
     * 观察下面的现象,某个数字的立方，按位累加仍然等于自身。
     * 1^3 = 1
     * 8^3  = 512    5+1+2=8
     * 17^3 = 4913   4+9+1+3=17
     * ...
     *
     * 请你计算包括1,8,17在内，符合这个性质的正整数一共有多少个？
     *
     * 答案: 6个 -> 1 8 17 18 26 27
     */
    private static void two() {
        int res = 0;
        for (int i = 1; ; i++) {
            int triple = i * i * i;
            if (i > Integer.toString(triple).length() * 9) break;
            int t = 0;
            while (triple != 0) {
                t += triple % 10;
                triple /= 10;
            }
            if (t == i) {
                System.out.println(i);
                res++;
            }
            System.out.println(i);
        }

        System.out.println(res);
    }

    /*
     * 3. 三羊献瑞
     *
     * 观察下面的加法算式：
     *
     *      祥 瑞 生 辉
     *      9  5  6
     *   +  三 羊 献 瑞
     *      1  0  8  5
     *   --------------
     *    三 羊 生 瑞 气
     *   1  0  6  5
     *
     * 其中，相同的汉字代表相同的数字，不同的汉字代表不同的数字
     * 请你填写“三羊献瑞”所代表的4位数字（答案唯一），不要填写任何多余内容
     */


    /**
     * 4. 循环节长度
     *
     * 两个整数做除法，有时会产生循环小数，其循环部分称为：循环节
     * 比如，11/13=6=>0.846153846153.....  其循环节为[846153] 共有6位
     * 下面的方法，可以求出循环节的长度
     * 请仔细阅读代码，并填写划线部分缺少的代码
     *
     * 	public static int f(int n, int m) {
     * 		n = n % m;
     * 		Vector v = new Vector();
     *
     * 		for(;;) {
     * 			v.add(n);
     * 			n *= 10;
     * 			n = n % m;
     * 			if(n==0) return 0;
     * 			if(v.indexOf(n)>=0)  _________________________________ ;  //填空
     *      }
     *  }
     *
     * 注意，只能填写缺少的部分，不要重复抄写已有代码。不要填写任何多余的文字
     */
    private static int four(int n, int m) {
        n = n % m;
        Vector<Integer> v = new Vector<>();

        for(;;) {
            v.add(n);
            n *= 10;
            n = n % m;
            if (n == 0) return 0;
            if (v.indexOf(n) >= 0) { //填空
                return v.size();
            }
        }
    }


    /**
     * 5. 九数组分数
     *
     * 1,2,3...9 这九个数字组成一个分数，其值恰好为1/3，如何组法？
     * 下面的程序实现了该功能，请填写划线部分缺失的代码。
     *
     * public class A
     * {
     * 	public static void test(int[] x)
     *        {
     * 		int a = x[0]*1000 + x[1]*100 + x[2]*10 + x[3];
     * 		int b = x[4]*10000 + x[5]*1000 + x[6]*100 + x[7]*10 + x[8];
     * 		if(a*3==b) System.out.println(a + " " + b);
     *    }
     *
     * 	public static void f(int[] x, int k)
     *    {
     * 		if(k>=x.length){
     * 			test(x);
     * 			return;
     *        }
     *
     * 		for(int i=k; i<x.length; i++){
     *            {int t=x[k]; x[k]=x[i]; x[i]=t;}
     * 			f(x,k+1);
     * 			_______________________________________       // 填空
     *        }
     *    }
     *
     * 	public static void main(String[] args)
     *    {
     * 		int[] x = {1,2,3,4,5,6,7,8,9};
     * 		f(x,0);
     *    }
     * }
     */
    private static void test(int[] x) {
        int a = x[0] * 1000 + x[1] * 100 + x[2] * 10 + x[3];
        int b = x[4] * 10000 + x[5] * 1000 + x[6] * 100 + x[7] * 10 + x[8];
        if (a * 3 == b) System.out.println(a + " " + b);
    }

    private static void five(int[] x, int k) {
        if (k >= x.length) {
            test(x);
            return;
        }

        for (int i = k; i < x.length; i++) {
            {
                int t = x[k];
                x[k] = x[i];
                x[i] = t;
            }
            five(x, k + 1);
            {
                int t = x[k];
                x[k] = x[i];
                x[i] = t;
            }
        }
    }


    /**
     * 6. 加法变乘法
     *
     * 我们都知道：1+2+3+ ... + 49 = 1225
     * 现在要求你把其中两个不相邻的加号变成乘号，使得结果为2015
     *
     * 比如：
     * 1+2+3+...+10*11+12+...+27*28+29+...+49 = 2015
     * 就是符合要求的答案。
     *
     * 请你寻找另外一个可能的答案，并把位置靠前的那个乘号左边的数字提交（对于示例，就是提交10)
     */
    private static void six() {
        for (int i = 1; i < 48; i++) {
            for (int j = i + 2; j < 50; j++) {
                if (i * (i + 1) + j * (j + 1) - (2 * i + 1) - (2 * j + 1) == 2015 - 1225)
                    System.out.println(i + " " + j);
            }
        }
    }


    /**
     * 7. 牌型种数
     *
     * 小明被劫持到X赌城，被迫与其他3人玩牌。
     * 一副扑克牌（去掉大小王牌，共52张），均匀发给4个人，每个人13张。
     * 这时，小明脑子里突然冒出一个问题：
     * 如果不考虑花色，只考虑点数，也不考虑自己得到的牌的先后顺序，自己手里能拿到的初始牌型组合一共有多少种呢？
     *
     * 请填写该整数，不要填写任何多余的内容或说明文字
     */
    private static int res = 0;
    private static void seven(int type, int sum) {
        if (sum > 13) return;

        if (type == 13) {
            if (sum == 13) res++;
            return;
        }
        for (int i = 0; i < 5; i++) {
            seven(type + 1, sum + i);
        }
    }


    /**
     * 8. 饮料换购
     *
     * 乐羊羊饮料厂正在举办一次促销优惠活动。乐羊羊C型饮料，凭3个瓶盖可以再换一瓶C型饮料，并且可以一直循环下去，但不允许赊账。
     * 请你计算一下，如果小明不浪费瓶盖，尽量地参加活动，那么，对于他初始买入的n瓶饮料，最后他一共能得到多少瓶饮料。
     *
     * 输入：一个整数n，表示开始购买的饮料数量（0<n<10000）
     * 输出：一个整数，表示实际得到的饮料数
     */
    private static int eight(int n) {
        return n + iter(n);
    }

    private static int iter(int n) {
        int res = 0;
        int remanet = n % 3;

        if (n < 3) return 0;

        res += n / 3;
        res += iter(n / 3 + remanet);

        return res;
    }



    public static void main(String[] args) {
//        int[] x = {1,2,3,4,5,6,7,8,9};
//        five(x,0);
//        six();
        seven(0, 0);
        System.out.println(res);
//        System.out.println(eight(101));
    }
}
