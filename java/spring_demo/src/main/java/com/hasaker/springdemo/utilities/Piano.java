package com.hasaker.springdemo.utilities;

import com.hasaker.springdemo.interfaces.Instrument;

public class Piano implements Instrument {

    public void play() {
        System.out.println("钢琴声响起");
    }
}
