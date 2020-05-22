package com.hasaker.springdemo.performers;

import com.hasaker.springdemo.interfaces.Poem;

public class PoeticJuggler extends Juggler {
    private Poem poem;

    public PoeticJuggler(Poem poem) {
        this.poem = poem;
    }

    public PoeticJuggler(Poem poem, int beanBags) {
        super(beanBags);
        this.poem = poem;
    }

    public void perform() {
        super.perform();
        System.out.println("边朗诵...");
        poem.recite();
    }
}
