package com.hasaker.spring_demo;

import com.hasaker.spring_demo.beans.Juggler;
import com.hasaker.spring_demo.factories.Stage;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Show {

    public static void main(String[] args) {
        String conf = "applicationContext.xml";
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(conf);
        Juggler duke = (Juggler) ac.getBean("poeticJuggler");
        Stage stage = (Stage) ac.getBean("stage");
        stage.createStage();
        duke.perform();

        ac.close();
    }
}
