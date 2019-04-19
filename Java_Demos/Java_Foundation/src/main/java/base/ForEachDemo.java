package base;

import java.util.ArrayList;
import java.util.List;


public class ForEachDemo {

    public static void main(String[] args) {
        List<String> userNames = new ArrayList<>() {{
            add("Eminem");
            add("Hasaker");
            add("Marhall");
            add("Dia");
            add("La");
        }};

        for (String userName : userNames) {
            if (userName.equals("Dia")) {
                userNames.remove(userName);
            }
        }

        System.out.println(userNames);
    }
}
