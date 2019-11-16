package com.telerikacademy.oop;

public class Person2 {
    public static final int NAME_MIN_LENGTH = 3;
    public static final String NAME_DEFAULT_VALUE = "Noname";

    private final String name;

    Person2(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.length() < NAME_MIN_LENGTH) {
            throw new IllegalArgumentException();
        }
    }

    Person2() {
        this(NAME_DEFAULT_VALUE);
    }

    public String getName() {
        return name;
    }

//    private void setName(String name) {
//        this.name = name;
//    }

    public static void main(String[] args) {
        Person2 p1 = new Person2("Pesho");
        System.out.println(p1.getName());

        Person2 p2 = new Person2();
        System.out.println(p2.getName());


    }
}
