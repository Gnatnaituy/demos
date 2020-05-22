package com.hasaker.springdemo.utilities;

import com.hasaker.springdemo.interfaces.Poem;

public class DeceivedByLife implements Poem {
    private static String[] LINES = {
            "假如生活欺骗了你，",
            "不要悲伤，不要心急！",
            "忧郁的日子里须要镇静：",
            "相信吧，快乐的日子将会来临！",
            "心儿永远向往着未来；",
            "现在却常是忧郁。",
            "一切都是瞬息，一切都将会过去；",
            "而那过去了的，就会成为亲切的怀恋"
    };

    public DeceivedByLife() {}

    @Override
    public void recite() {
        for (String line : LINES) {
            System.out.println(line);
        }
    }
}
