package design_pattern.structural.proxy;

/**
 * @package designpartten.proxy
 * @author 余天堂
 * @create 2020/5/29 23:35
 * @description HugeObject
 */
public class HugeObject implements IHugeObject {

    public HugeObject() {
        try {
            // 假设这个复杂的对象初始化需要一秒时间
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void complexMethod() {
        System.out.println("This is a huge object with complex method!");
    }
}
