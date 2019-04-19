package com.hasaker.spring_demo.utilities;

import com.hasaker.spring_demo.interfaces.Instrument;

public class Saxophone implements Instrument {

    @Override
    public void play() {
        System.out.println("萨克斯响起");
    }
}
