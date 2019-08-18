package com.hasaker.springdemo.commonbeans;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 指定扫描路径
 * @ComponentScan(basePackages="com.hasaker")
 * @ComponentScan(basePackages={"com.hasaker", "com.spring.dao"})
 * 指定扫描类型
 * @ComponentScan(basePackageClasses={City.class, Country.class})
 */
@Configuration
public class CommonConfig {

    @Bean
    public Country country() {
        return new Country();
    }

    @Bean
    public ContestantIntroducer contestantIntroducer() {
        return new ContestantIntroducer();
    }
}
