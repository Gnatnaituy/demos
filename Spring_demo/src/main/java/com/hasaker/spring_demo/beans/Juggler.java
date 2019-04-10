package com.hasaker.spring_demo.beans;

import com.hasaker.spring_demo.interfaces.Performer;

public class Juggler implements Performer {

    private int beanBags = 3;

    public Juggler() {}

    public Juggler(int beanBags) {
        this.beanBags = beanBags;
    }

    public void perform() {
        System.out.println("Throws " + beanBags + " beanBags");
    }
}
