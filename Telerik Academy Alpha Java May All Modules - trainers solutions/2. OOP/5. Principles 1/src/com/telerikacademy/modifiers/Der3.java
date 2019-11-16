package com.telerikacademy.modifiers;

public class Der3 extends Der1 {
    public Der3(){
        System.out.println("der3 constructor");
    }

    public int method3(int i){
        System.out.println("****Method3 in Der3");
        return i++;
    }

    @Override
    public Der3 getDer1() {
        super.method3();
        return new Der3();
    }
}
