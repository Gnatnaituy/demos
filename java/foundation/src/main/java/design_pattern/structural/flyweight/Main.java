package design_pattern.structural.flyweight;

/**
 * @package design_pattern.flyweight
 * @author 余天堂
 * @create 2020/5/29 23:52
 * @description Main
 */
public class Main {

    /**
     * Flyweight is a structural design pattern that lets you fit more objects into the available amount of RAM
     * by sharing common parts of state between multiple objects instead of keeping all of the data in each object.
     * @param args
     */
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
