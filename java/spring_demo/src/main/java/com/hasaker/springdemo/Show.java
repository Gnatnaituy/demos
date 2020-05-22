package com.hasaker.springdemo;

import com.hasaker.springdemo.performers.*;
import com.hasaker.springdemo.factories.Stage;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Show {

    public static void main(String[] args) {
        String conf = "applicationContext.xml";
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(conf);

        Juggler duke = (Juggler) ac.getBean("poeticJuggler");
        Stage stage = (Stage) ac.getBean("stage");
        Instrumentalist kenny = (Instrumentalist) ac.getBean("kenny");
        OneManBan hank = (OneManBan) ac.getBean("hank");
        OneManBan dody = (OneManBan) ac.getBean("dody");
        OneManBan eminem = (OneManBan) ac.getBean("eminem");
        Instrumentalist carl = (Instrumentalist) ac.getBean("carl");
        Volunteer volunteer = (Volunteer) ac.getBean("volunteer");
        Magician magician = (Magician) ac.getBean("magician");
//        Constant eminemContestant = (Constant) ac.getBean("eminem");

//        stage.createStage();
//        duke.perform();
//        kenny.perform();
//        hank.perform();
//        dody.perform();
//        eminem.perform();
        carl.perform();

//        ChooseCity chooseCity = (ChooseCity) ac.getBean("chooseCity");
//        System.out.println(chooseCity.getCity().getName());
//
//        ChooseCity randomChooseCity = (ChooseCity) ac.getBean("randomChooseCity");
//        System.out.println(randomChooseCity.getCity().getName());
//
//        ChooseCities chooseCities = (ChooseCities) ac.getBean("chooseCities");
//        for (City city : chooseCities.getCities()) {
//            System.out.println(city.getName());
//        }

//        Country china = (Country) ac.getBean("country");
//        china.showCountry();

        volunteer.thinkOfSomething("你是大傻逼");
        System.out.println("志愿者心中想的是: " + magician.getThoughts());

        ac.close();
    }
}
