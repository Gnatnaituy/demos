package designpartten.proxy;

import java.util.Date;

/**
 * @package designpartten.proxy
 * @author 余天堂
 * @create 2020/5/29 23:39
 * @description Main
 */
public class Main {

    public static void main(String[] args) {
        IHugeObject hugeObject = new HugeObjectProxy();
        System.out.println(new Date() + ": 代理类创建完成");
        hugeObject.complexMethod();
    }
}
