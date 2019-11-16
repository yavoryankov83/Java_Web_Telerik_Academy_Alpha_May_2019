package com.telerikacademy.modifiers;

public class Base {
    public int id = 1;
//    public Base() {
//        System.out.println("Base default constructor");
//    }

    public Base(String str) {
        System.out.println("Base constructor");
    }

    private void method() {

    }

    void method1() {
        System.out.println("Base method1");
    }

    protected void method2() {
        System.out.println("Base method2");
    }

    protected Der1 getDer1() {
        return new Der1();
    }
}
