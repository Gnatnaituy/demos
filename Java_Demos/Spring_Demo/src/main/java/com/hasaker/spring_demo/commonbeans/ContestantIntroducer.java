package com.hasaker.spring_demo.commonbeans;


import com.hasaker.spring_demo.interfaces.Contestant;
import com.hasaker.spring_demo.performers.OutstandingContestant;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class ContestantIntroducer {

    @DeclareParents(value = "com.hasaker.spring_demo.interfaces.Performer+",
                    defaultImpl = OutstandingContestant.class)
    public static Contestant contestant;
}
