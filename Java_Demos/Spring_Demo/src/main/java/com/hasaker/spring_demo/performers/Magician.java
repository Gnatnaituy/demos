package com.hasaker.spring_demo.performers;

import com.hasaker.spring_demo.interfaces.MindReader;

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
