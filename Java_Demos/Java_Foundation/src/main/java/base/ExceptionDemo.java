package base;

/**
 * @auther hasaker
 * @create_date 2019-08-12 15:54
 * @description
 */
public class ExceptionDemo {

    public static void main(String[] args) {
        String[] students = {"aaa", "bbb", "ccc"};

        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(students[i]);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("index error!");
        } finally {
            System.out.println("This is end!");
        }

        System.out.println(exceptionDemo());
    }

    private static int exceptionDemo() {
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("aaa");
            return -1;
        } finally {
            return 0;
        }
    }
}
