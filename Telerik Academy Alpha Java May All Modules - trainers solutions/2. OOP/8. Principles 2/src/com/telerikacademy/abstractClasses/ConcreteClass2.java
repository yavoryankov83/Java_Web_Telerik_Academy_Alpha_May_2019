package com.telerikacademy.abstractClasses;

public class ConcreteClass2 extends AbstractClass2 {
    @Override
    public void method4() {
        System.out.println("ConcreteClass2 method 4");
    }

    @Override
    public void method2() {
        System.out.println("ConcreteClass2 method 2");
    }

    @Override
    protected String getDetails() {
        return "I am sooo concrete ^ 2";
    }
}
