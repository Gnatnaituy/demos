package com.hasaker.springdemo.performers;

import com.hasaker.springdemo.interfaces.Thinker;

public class Volunteer implements Thinker {
    private String thoughts;

    @Override
    public void thinkOfSomething(String thoughts) {
        this.thoughts = thoughts;
    }

    public String getThoughts() {
        return thoughts;
    }
}
