package com.hasaker.spring_demo.commonbeans;


import org.springframework.beans.factory.annotation.Value;

/*
 * @Component —— 通用的构造型注解，标识该类为Spring组件。
 * @Controller —— 标识将该类定义为Spring MVC Controller组件。
 * @Repository —— 标识将该类定义为数据仓库。
 * @Service —— 标识将该类定义为服务。
 */

public class Country {
    @Value("China")
    private String name;

    @Value("1300000")
    private int population;

    public Country() {}

    public Country(String name, int population) {
        this.name = name;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void showCountry() {
        System.out.println("Country name: " + name + " population: " + population);
    }
}
