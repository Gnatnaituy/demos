package siki.javabaseiii;


public class AlterableParameter {

    public static void main(String[] args) {
        print("hello");
        print("heeloll", "asdasd");
    }

    public static void print(String str, String... args) {
        System.out.println(str + "------");
        for (String arg : args) {
            System.out.println(arg);
        }
    }
 
    public static void print(String arg) {
        System.out.println("优先选用固定参数" + arg);
    }
}