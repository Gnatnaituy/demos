package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorDemo {

    public static void main(String[] args) {
        List<String> strList = new ArrayList<>(Arrays.asList("a", "b", "c"));
        Iterator<String> iterator = strList.iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        List<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0));
        Iterator<Integer> itrOne = intList.iterator();
        Iterator<Integer> itrTwo = intList.iterator();

        for (; itrOne.hasNext();) {
            if (itrOne.next().equals(5)) {
                itrOne.remove(); // #1
                //intList.remove(itrOne.next()); // #2
            }
        }

        for (; itrTwo.hasNext();) {
            if (itrTwo.next().equals(5)) {
                itrTwo.remove(); // #1
                //intList.remove(itrTwo.next()); // #2
            }
        }

        intList.forEach(System.out::println);
    }
}
