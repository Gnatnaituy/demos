package com.hasaker.springdemo.commonbeans;


import com.hasaker.springdemo.interfaces.Contestant;
import com.hasaker.springdemo.performers.OutstandingContestant;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class ContestantIntroducer {

    @DeclareParents(value = "com.hasaker.springdemo.interfaces.Performer+", defaultImpl = OutstandingContestant.class)
    public static Contestant contestant;
}
