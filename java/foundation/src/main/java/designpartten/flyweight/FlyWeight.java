package designpartten.flyweight;

/**
 * @package designpartten.flyweight
 * @author 余天堂
 * @create 2020/5/29 23:47
 * @description FlyWeight
 */
public class FlyWeight implements IFlyWeight {

    protected String flyWeightType;

    public FlyWeight(String flyWeightType) {
        this.flyWeightType = flyWeightType;
    }

    @Override
    public void method() {
        System.out.println("This is a instance for " + this.flyWeightType);
    }
}
