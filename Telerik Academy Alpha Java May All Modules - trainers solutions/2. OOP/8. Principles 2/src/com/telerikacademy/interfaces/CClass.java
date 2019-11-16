package com.telerikacademy.interfaces;

public class CClass extends AbstractClass implements MyInterface, Iterfacable {

    @Override
    public void method1() {
        // some logic here
        System.out.println("CClass method1");
    }

    @Override
    public void method2() {
        System.out.println(Iterfacable.i);
    }
}
