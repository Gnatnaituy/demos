package base;

import java.util.ArrayList;
import java.util.List;


public class ForEachDemo {

    public static void main(String[] args) {
        List<String> userNames = new ArrayList<>() {{
            add("Eminem");
            add("Hasaker");
            add("Marshall");
            add("Dia");
            add("La");
        }};

        for (String userName : userNames) {
            if (userName.equals("Dia")) {
                userNames.remove(userName);
            }
        }

        // throw java.util.ConcurrentModificationException
//        userNames.forEach(o -> {
//            if (o.equals("La")) {
//                userNames.remove(o);
//            }
//        });

        System.out.println(userNames);


        // List.of从给定的参数创建了一个新的不可变列表
        // List.copyOf创建列表的不可变副本
        List<String> sizes = new ArrayList<>(List.of("Big", "Medium", "Small"));
        var sizesCopy = List.copyOf(sizes);
        System.out.println(sizesCopy);
        sizes.removeIf(o -> o.equals("Small"));
        System.out.println(sizes);
    }
}
