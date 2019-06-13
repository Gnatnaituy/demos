package com.hasaker.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

@SpringBootApplication
public class HadoopDriveApplication {

    public static void main(String[] args) {
        SpringApplication.run(HadoopDriveApplication.class, args);
        System.out.println(ResourceUtils.CLASSPATH_URL_PREFIX + "mongo-config.properties");
        MongoDemo.testMongo();
    }

}
