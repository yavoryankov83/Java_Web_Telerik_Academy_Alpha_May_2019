package com.telerikacademy;

public class Main {

    public static void main(String[] args) {
        Person p1 = new Person("Pesho", 32, "0887197953");

        p1.introduce();
        p1.sharePhoneNumber();

        int a = 10;
        test(p1, a);
        System.out.println(p1.getName());
        System.out.println(a);

//        Person p2 = null;
//        System.out.println(p2.name);

        Person p3 = new Person("Ivan");
        p3.sharePhoneNumber();
    }

    static void test(Person p, int x) {
        p.setName("Gosho");
        x = 20;

        p = new Person("", 0, "");
        p.setName("Sasho");
    }
}
