package com.telerikacademy.abstractClasses;

public class ConcreteClass extends AbstractClass2 {
    @Override
    public void method4() {
        System.out.println("ConcreteClass method 4");
    }

    @Override
    public void method2() {
        System.out.println("ConcreteClass method 2");
    }

    @Override
    protected String getDetails() {
        return "I am sooo concrete";
    }
}
