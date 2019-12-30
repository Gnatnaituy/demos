package base;


import java.util.List;

public class GenericDemo {

    // Generic method -> printArray
    private static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }

    /**
     * 有界的类型参数:
     * 可能有时候，你会想限制那些被允许传递到一个类型参数的类型种类范围。
     * 例如，一个操作数字的方法可能只希望接受Number或者Number子类的实例。
     * 这就是有界类型参数的目的。
     * 要声明一个有界的类型参数，首先列出类型参数的名称，后跟extends关键字，最后紧跟它的上界。
     */
    private static <T extends Comparable<T>> T maximum(T x, T y, T z) {
        T max = x;

        if (y.compareTo(max) > 0) {
            max = y;
        }
        if (z.compareTo(max) > 0) {
            max = z;
        }

        return max;
    }

    /**
     * 泛型类的声明和非泛型类的声明类似，除了在类名后面添加了类型参数声明部分。
     * 和泛型方法一样，泛型类的类型参数声明部分也包含一个或多个类型参数，参数间用逗号隔开
     * 一个泛型参数，也被称为一个类型变量，是用于指定一个泛型类型名称的标识符
     * 因为他们接受一个或多个参数，这些类被称为参数化的类或参数化的类型
     */
    static class Box<T> {
        private T t;

        Box(T t) {
            this.t = t;
        }

        void set(T t) {
            this.t = t;
        }

        T get() {
            return t;
        }
    }

    /**
     * 类型通配符
     */
    private static void getData(Box<?> box) {
        System.out.println(box.get());
    }


    public static void main(String[] args) {
        Integer[] integers = {1, 2, 3, 4, 5};
        Double[] doubles = {1.0, 2.3, 3.4, 4.555, 5.666};
        Character[] characters = {'h', 'a', 's', 'a', 'k', 'e', 'r'};
        printArray(integers);
        printArray(doubles);
        printArray(characters);

        System.out.printf("%d, %d 和 %d 中最大的数为 %d\n", 3, 4, 5, maximum(3, 4, 5));
        System.out.printf("%f, %f 和 %f 中最大的数为 %f\n", 3.2, 4.5, 5.9, maximum(3.2, 4.5, 5.9));
        System.out.printf("%s, %s 和 %s 中最大的数为 %s\n",
                "apple", "dog", "shit", maximum("apple", "dog", "shit"));

        Box<Integer> integerBox = new Box<>(100);
        Box<Double> doubleBox = new Box<>(1000.00);
        Box<String> stringBox = new Box<>("Nice Shit!");
        List<Box> boxes = List.of(integerBox, doubleBox, stringBox);
        boxes.forEach(box -> System.out.println(box.get()));
        boxes.forEach(GenericDemo::getData);
    }
}
