package designpartten.flyweight;

/**
 * @package designpartten.flyweight
 * @author 余天堂
 * @create 2020/5/29 23:52
 * @description Main
 */
public class Main {

    public static void main(String[] args) {
        FlyWeightFactory flyWeightFactory = new FlyWeightFactory();
        IFlyWeight flyWeightA = flyWeightFactory.getFlyWeight("A");
        IFlyWeight flyWeightB = flyWeightFactory.getFlyWeight("B");
        IFlyWeight flyWeightC = flyWeightFactory.getFlyWeight("A");
        flyWeightA.method();
        flyWeightB.method();
        flyWeightC.method();
    }
}
