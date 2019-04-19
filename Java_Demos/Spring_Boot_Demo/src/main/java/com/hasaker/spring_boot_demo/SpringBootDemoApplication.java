package com.hasaker.spring_boot_demo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication demo = new SpringApplication(SpringBootDemoApplication.class);
        demo.setBannerMode(Banner.Mode.OFF);
        demo.run(args);
    }
}