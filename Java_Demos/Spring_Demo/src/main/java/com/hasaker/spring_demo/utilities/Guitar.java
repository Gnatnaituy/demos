package com.hasaker.spring_demo.utilities;

import com.hasaker.spring_demo.interfaces.Instrument;

public class Guitar implements Instrument {

    @Override
    public void play() {
        System.out.println("吉他响起");
    }
}
