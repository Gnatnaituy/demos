package design_pattern.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @package design_pattern.flyweight
 * @author 余天堂
 * @create 2020/5/29 23:46
 * @description FlyWeightFactory
 */
public class FlyWeightFactory {

    Map<String, IFlyWeight> flyWeights = new HashMap<>();

    IFlyWeight getFlyWeight(String type) {
        IFlyWeight flyWeight = flyWeights.get(type);
        if (flyWeight == null) {
            flyWeight = new FlyWeight(type);
            flyWeights.put(type, flyWeight);
        }

        System.out.println("现在我有" + flyWeights.size() + "个对象实例！");

        return flyWeight;
    }
}
