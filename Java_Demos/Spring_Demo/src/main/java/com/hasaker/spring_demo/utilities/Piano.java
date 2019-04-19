package com.hasaker.spring_demo.utilities;

import com.hasaker.spring_demo.interfaces.Instrument;

public class Piano implements Instrument {

    public void play() {
        System.out.println("钢琴声响起");
    }
}
