package design_pattern.creational.singleton;

/**
 * @package design_pattern.singleton
 * @author 余天堂
 * @create 2020/5/29 23:17
 * @description Singleton
 */
public class Singleton {

    // 构造函数必须是private级别的
    private Singleton() {
        System.out.println("Singleton is created!");
    }

    // instance成员变量需是static的
    private static final Singleton instance = new Singleton();

    // getInstance()方式需是static的
    public static Singleton getInstance() {
        return instance;
    }
}
