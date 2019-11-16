package com.telerikacademy.ldstofillFromSession.linkedlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CreateLinkedListExample {
    public static void main(String[] args) {

        // Creating a LinkedList
        LinkedList<String> friends = new LinkedList<>();

        // Adding new elements to the LinkedList using add() method.
        friends.add("Phoebe");
        friends.add("Joey");
        friends.add("Rachel");
        friends.add("Ross");
        friends.add("Monica");
        friends.add("Chandler");

        System.out.println("Initial LinkedList : " + friends);

        // Adding an element at the end of the LinkedList (This method is equivalent to the add() method)
        friends.addLast("Janice");
        System.out.println("After addLast(\"Janice\") : " + friends);

        // Adding an element at the specified position in the LinkedList
        friends.add(3, "Gunter");

        System.out.println("After add(3, \"Gunter\") : " + friends);

        // Adding an element at the beginning of the LinkedList using addFirst
        friends.addFirst("Mike");
        System.out.println("After addFirst(\"Mike\") : " + friends);

        // Adding all the elements from an new existing collection to the end of the LinkedList
        List<String> moreFriendsCast = new ArrayList<>();
        moreFriendsCast.add("Emma");
        moreFriendsCast.add("Jack");
        moreFriendsCast.add("David");

        friends.addAll(2, moreFriendsCast);

        System.out.println("After addAll(moreFriendsCast) : " + friends);
    }
}