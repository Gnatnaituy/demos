package com.hasaker.spring_boot_demo.bean;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -339516038496531943L;
    private int id;
    private String username;
    private int age;
    private String password;

    public User() {}

    public User(int id, String username, int age, String password) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
