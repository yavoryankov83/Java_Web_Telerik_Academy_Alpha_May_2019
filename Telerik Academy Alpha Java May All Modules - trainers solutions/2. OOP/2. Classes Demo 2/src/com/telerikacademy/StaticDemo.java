package com.telerikacademy;

public class StaticDemo {
    private static String name;
    private static Person[] people;

    static {
        name = "Gosho";

        people = new Person[5];

        people[0] = new Person("Name1", 3, "Phone1");
        people[1] = new Person("Name2", 3, "Phone2");
        people[2] = new Person("Name3", 3, "Phone3");
        people[3] = new Person("Name4", 3, "Phone4");
        people[4] = new Person("Name5", 3, "Phone5");
    }

    public static void printName() {
        System.out.println(StaticDemo.name);
    }

    public static void main(String[] args) {
        StaticDemo.printName();

        System.out.println(Math.sqrt(5));
    }
}
