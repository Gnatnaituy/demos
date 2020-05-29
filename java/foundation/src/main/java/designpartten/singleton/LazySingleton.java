package designpartten.singleton;

/**
 * @package designpartten.singleton
 * @author 余天堂
 * @create 2020/5/29 23:21
 * @description LazySingleton
 */
public class LazySingleton {

    private LazySingleton() {
        System.out.println("LazySingleton is created!");
    }

    /**
     * 对静态成员变量赋初始值为null，确保系统启动时没有额外的负载
     */
    private static LazySingleton instance = null;

    /**
     * getInstance()方法需是同步的， 避免多线程环境下小概率出现的创建多个实例的情况
     * 由于引入了同步关键字，所以会在多线程环境中会造成额外的开销
     * @return
     */
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }

        return instance;
    }
}
