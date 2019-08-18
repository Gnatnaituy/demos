package com.hasaker.springdemo.performers;

import org.aspectj.lang.ProceedingJoinPoint;

public class Audience {

    // 表演之前
    public void takeSeats() {
        System.out.println("观众入座");
    }

    // 表演之前
    public void turnOffCellPhone() {
        System.out.println("关闭手机");
    }

    // 表演之后
    public void applaud() {
        System.out.println("观众鼓掌 啪啪啪啪啪啪啪啪");
    }

    // 表演失败之后
    public void performFailed() {
        System.out.println("坑爹啊!退钱!!!");
    }

    // 声明环绕通知
    public void watch(ProceedingJoinPoint joinPoint) {
        try {
            takeSeats();
            turnOffCellPhone();
            long start = System.currentTimeMillis();

            // 执行被通知的方法
            joinPoint.proceed();

            long end = System.currentTimeMillis();
            applaud();
            System.out.println("表演耗时: " + (end - start) + "ms");
        } catch (Throwable t) {
            performFailed();
        }
    }
}
