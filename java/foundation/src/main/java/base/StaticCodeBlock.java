package base;

/**
 * @author hasaker
 * @create_date 2019-08-19 09:46
 * @description
 */
public class StaticCodeBlock extends Parent {

    static {
        System.out.println("Son 静态代码块");
    }

    private StaticCodeBlock() {
        System.out.println("Son 构造方法");
    }

    /**
     * 静态代码块只运行一次, 在的第二次对象实例化是不会运行
     */
    public static void main(String[] args) {
        new StaticCodeBlock();
        new StaticCodeBlock();
        new StaticCodeBlock();
    }
}


class Parent {
    static {
        System.out.println("Parent 静态代码块");
    }

    Parent() {
        System.out.println("Parent 构造方法");
    }
}
