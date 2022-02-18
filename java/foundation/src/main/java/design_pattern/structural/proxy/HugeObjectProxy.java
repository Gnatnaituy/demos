package design_pattern.structural.proxy;

import java.util.Date;

/**
 * @package designpartten.proxy
 * @author 余天堂
 * @create 2020/5/29 23:37
 * @description HugeObjectProxy
 */
public class HugeObjectProxy implements IHugeObject {

    private HugeObject realHugeObject = null;

    /**
     * 当这个接口被用到时才创建真实的对象
     */
    @Override
    public void complexMethod() {

        System.out.println(new Date() + ": 方法被调用，创建真实对象");

        if (realHugeObject == null) {
            this.realHugeObject = new HugeObject();
        }

        System.out.println(new Date() + ": 真实对象创建成功，调用方法");
        realHugeObject.complexMethod();
    }
}
