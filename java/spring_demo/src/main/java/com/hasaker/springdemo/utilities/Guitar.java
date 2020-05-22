package com.hasaker.springdemo.utilities;

import com.hasaker.springdemo.interfaces.Instrument;

public class Guitar implements Instrument {

    @Override
    public void play() {
        System.out.println("吉他响起");
    }
}
