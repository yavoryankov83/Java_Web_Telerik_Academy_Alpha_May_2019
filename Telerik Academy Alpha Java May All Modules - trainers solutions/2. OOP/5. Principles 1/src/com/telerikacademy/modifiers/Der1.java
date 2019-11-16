package com.telerikacademy.modifiers;

public class Der1 extends Base {
    public int id =21;
    public Der1() {
        super("ffdsdf");
        System.out.println("Der1 constructor");
    }

    public void method3() {
        System.out.println("******Method3 in Der1");
        method1();
        method2();
    }
}
