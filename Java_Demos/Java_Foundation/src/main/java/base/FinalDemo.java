package base;

public class FinalDemo {

    // string: 外部类的实例变量
    private String string = "";

    // ch: 方法的参数
    private void outerTest(final char ch) {

        // integer: 方法内局部变量
        final Integer integer = 1;

        new Inner() {
             void innerTest() {
                System.out.println(string);
                System.out.println(ch);
                System.out.println(integer);
            }
        };
    }

    public static void main(String[] args) {
        new FinalDemo().outerTest('H');
    }

    private class Inner {
    }

}
