package com.telerikacademy;

public class Person {
    private String name;
    private int age;
    private String phone;

    public Person(String name, int age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    Person(String name) {
        this(name, 0, "");
    }
}
