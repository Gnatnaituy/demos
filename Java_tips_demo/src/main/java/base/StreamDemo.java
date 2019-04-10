package base;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {
        List<Integer> digits = new ArrayList<>();
        List<Integer> res;

        for (int i = 0; i < 10; i++) {
            digits.add(i);
            digits.add(i - 5);
        }

//        filter map limit/skip sorted distinct
        digits.stream().map(i -> i * i).forEach(System.out::print);
        digits.stream().filter(integer -> integer % 2 == 0).forEach(System.out::print);
//        digits.stream().sorted().forEach(res::add);
//        digits.stream().distinct().forEach(res::add);
        res = digits.stream().distinct().collect(Collectors.toList());
        res.forEach(System.out::print);

        // 输出10个随机数字
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

        // 斐波那契数列
        Stream.iterate(new BigInteger[]{BigInteger.ONE, BigInteger.ONE},
                        p -> new BigInteger[]{p[1], p[0].add(p[1])})
                        .limit(15).forEach(p -> System.out.print(p[0] + " "));

        Stream.iterate(new long[]{1, 1}, p -> new long[]{p[1], p[0] + p[1]})
                .limit(90).skip(89).forEach(p -> System.out.print(p[1] + " "));

        System.out.println(Stream.iterate(new long[]{1, 1}, p -> new long[]{p[1], p[0] + p[1]})
        .limit(90).skip(89).findFirst().get()[1]);
    }
}
