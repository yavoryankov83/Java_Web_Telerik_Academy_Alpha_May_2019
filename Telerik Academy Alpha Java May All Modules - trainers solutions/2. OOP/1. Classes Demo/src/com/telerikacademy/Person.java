package com.telerikacademy;

public class Person {
    private String name;
    private int age;
    private String phone;

    public Person() {
        this("", 0, "");
    }

    public Person(String name) {
        this(name, 0, "");
    }

    public Person(String name, int age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if (age < 0) {
            this.age = 0;
        } else {
            this.age = age;
        }
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void introduce() {
        System.out.printf("Hello, my name is %s and I am %d years old :)\n", name, age);
    }

    public void sharePhoneNumber() {
        System.out.printf("My phone number is %s\n", phone);
    }
}
