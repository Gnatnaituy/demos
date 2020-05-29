package designpartten.singleton;

/**
 * @package designpartten.singleton
 * @author 余天堂
 * @create 2020/5/29 23:27
 * @description InnerClassSingleton
 */
public class InnerClassSingleton {

    private InnerClassSingleton() {
        System.out.println("InnerClassSingleton is created!");
    }

    /**
     * 当InnerClassSingleton创建时，内部类SingletonHolder不会被创建
     */
    private static class SingletonHolder {
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    /**
     * getInstance()方法被调用时，才会加载SingletonHolder类，从而初始化instance
     * 由于实例的创建是在类加载时完成的，故天生对多线程友好
     * @return
     */
    public static InnerClassSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
