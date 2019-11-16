package com.telerikacademy.set;

import com.telerikacademy.Person;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
    public static void main(String[] args) {
//        Set<Integer> mySet = new HashSet();
//
//        mySet.add(1);
//        mySet.add(5);
//        mySet.add(2);
//        mySet.add(-11);
//
//        System.out.println(mySet.size());
//
//        mySet.add(5);
//        System.out.println(mySet.size());
//        System.out.println(mySet);
//
//        set.add("kalina");
//
//        set.add("vladi");
//        set.add("123");
//
//        System.out.println(set);
//
//        mySet.add(11);
//        mySet.add(12);
//        System.out.println(mySet);
//
//        System.out.println(mySet.add(12));
//

//        Set<String> set = new HashSet<>();

        Set<Person> people = new HashSet<>();
        Person ben = new Person("Ben", "Johnson");
        Person john = new Person("John", "Smith");
        Person zon = new Person("Zon", "Zoo");
        System.out.println(people.size());
        System.out.println(people);

        people.add(ben);
        people.add(ben);
        System.out.println(people);

        Set<Person> sortedPeople = new TreeSet<>();
        people.clear();
        sortedPeople.add(ben);
        people.add(ben);
        sortedPeople.add(john);
        people.add(john);
        sortedPeople.add(zon);
        people.add(zon);

        System.out.println(sortedPeople);
        System.out.println(people);

        Set<String> colors = new TreeSet<>();
        colors.add("green");
        colors.add("red");
        colors.add("zeleno");
        System.out.println(colors);

        sortedPeople.stream().filter(person -> person.getFirstName().equals("John"));
    }
}
