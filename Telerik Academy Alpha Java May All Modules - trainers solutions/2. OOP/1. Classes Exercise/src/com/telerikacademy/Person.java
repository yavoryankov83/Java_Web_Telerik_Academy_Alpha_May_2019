package com.telerikacademy;

import java.util.ArrayList;
import java.util.Arrays;

public class Person {
    private String name;
    private int age;
    private String phone;
    private ArrayList<String> interests;
    private ArrayList<Person> friends;

    public Person(String name, int age, String phone, String... interests) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.interests = new ArrayList<>(Arrays.asList(interests));
        friends = new ArrayList<>();
    }

    public void addFriend(Person friend) {
        friends.add(friend);
    }

    public void introduce() {
        System.out.printf("Hey, I am %s and I am %d years old :)\n", name, age);
    }

    public void sharePhone() {
        System.out.printf("You can contact me at %s\n", phone);
    }

    public void showFriends() {
        System.out.print("My friends are: ");

        for (Person friend : friends) {
            System.out.printf("%s ", friend.name);
        }
    }
}
