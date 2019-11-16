package com.telerikacademy.abstractClasses;

import java.util.stream.Stream;

public abstract class AbstractClass {
    private final String name = "Biggest Abstract";

    public abstract void method1();
    protected abstract String getDetails();
    public abstract void method2();

    public void method3(){
        System.out.println("Abstract 1 method 3");
    }

    public String getName(){
        return name + System.lineSeparator() + getDetails();
    }
}
