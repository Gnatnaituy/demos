package base;

/**
 * @author hasaker
 * @create_date 2019-08-18 23:34
 * @description
 */
public class ParamPassing {
    private static StringBuilder stringBuilder = new StringBuilder("Old StringBuilder");

    public static void main(String[] args) {
        method(stringBuilder, stringBuilder);
        System.out.println(stringBuilder);
    }

    private static void method(StringBuilder sb1, StringBuilder sb2) {
        // 直接使用参数引用修改对象
        // 复制stringBuilder引用给sb1, 所以stringBuilder和sb1指向相同的对象
        sb1.append(".method.first-");
        sb2.append("method.second-");

        // 引用重新赋值
        // sb1指向了新的对象, 与stringBuilder指向的对象已无联系
        sb1 = new StringBuilder("Bew StringBuilder");
        sb1.append("new method's append");
    }
}
