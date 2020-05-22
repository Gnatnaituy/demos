package com.hasaker.springdemo.utilities;

import com.hasaker.springdemo.interfaces.Instrument;

public class Saxophone implements Instrument {

    @Override
    public void play() {
        System.out.println("萨克斯响起");
    }
}
