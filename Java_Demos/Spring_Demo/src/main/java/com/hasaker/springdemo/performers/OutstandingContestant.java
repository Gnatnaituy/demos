package com.hasaker.springdemo.performers;

import com.hasaker.springdemo.interfaces.Contestant;

public class OutstandingContestant implements Contestant {

    @Override
    public void receiveAward() {
        System.out.println("参加颁奖典礼");
    }
}
