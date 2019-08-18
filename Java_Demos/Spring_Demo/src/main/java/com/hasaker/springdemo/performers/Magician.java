package com.hasaker.springdemo.performers;

import com.hasaker.springdemo.interfaces.MindReader;

public class Magician implements MindReader {

    private String thoughts;

    @Override
    public void interceptThoughts(String thoughts) {
        System.out.println("侦听倾愿者的心声");
        this.thoughts = thoughts;
    }

    @Override
    public String getThoughts() {
        return thoughts;
    }
}
