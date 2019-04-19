package com.hasaker.spring_demo.performers;

import com.hasaker.spring_demo.interfaces.Contestant;

public class OutstandingContestant implements Contestant {

    @Override
    public void receiveAward() {
        System.out.println("参加颁奖典礼");
    }
}
