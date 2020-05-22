package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorDemo {

    public static void main(String[] args) {
        List<String> strList = new ArrayList<>(Arrays.asList("a", "b", "c"));
        List<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0));

        for (Iterator<String> iterator = strList.iterator(); iterator.hasNext();) {
            System.out.print(iterator.next() + " ");
        }

        intList.removeIf(integer -> integer % 2 == 0);

        for (Iterator<Integer> itrTwo = intList.iterator(); itrTwo.hasNext();) {
            if (itrTwo.next().equals(5)) {
                itrTwo.remove();
            }
        }

        intList.forEach(System.out::print);
        intList.forEach((integer) -> System.out.print(integer + " "));
    }
}
