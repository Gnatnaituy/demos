package com.hasaker.springdemo.factories;

public class Stage {

    private Stage() {}

    // 延迟加载实例
    public static class stageSingletonHolder {
        static Stage instance = new Stage();
    }

    // 返回实例
    public static Stage getInstance() {
        return stageSingletonHolder.instance;
    }

    public void createStage() {
        System.out.println("创造一个舞台");
    }
}
