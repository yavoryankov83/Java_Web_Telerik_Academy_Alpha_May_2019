package com.telerikacademy.ldstofillFromSession.linkedlist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class IterateOverLinkedListExample {
    public static void main(String[] args) {

        // Create LinkedList with Human species
        LinkedList<String> humanSpecies = new LinkedList<>();

        humanSpecies.add("Homo Sapiens");
        humanSpecies.add("Homo Neanderthalensis");
        humanSpecies.add("Homo Erectus");
        humanSpecies.add("Home Habilis");

        System.out.println("=== Iterate over a LinkedList using Java 8 forEach and lambda ===");
        humanSpecies.forEach(System.out::println);

        System.out.println("\n=== Iterate over a LinkedList using iterator() ===");
        Iterator<String> iterator = humanSpecies.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("\n=== Iterate over a LinkedList using iterator() and Java 8 forEachRemaining() method ===");
        iterator = humanSpecies.iterator();
        iterator.forEachRemaining(System.out::println);

        System.out.println("\n=== Iterate over a LinkedList using descendingIterator() ===");
        iterator = humanSpecies.descendingIterator();
        iterator.forEachRemaining(System.out::println);

        System.out.println("\n=== Iterate over a LinkedList using listIterator() ===");
        // ListIterator can be used to iterate over the LinkedList in both forward and backward directions
        // In this example, we start from the end of the list and traverse backwards
        ListIterator<String> listIterator = humanSpecies.listIterator(humanSpecies.size());
        while (listIterator.hasPrevious()){
            System.out.println(listIterator.previous());
        }

        System.out.println("\n=== Iterate over a LinkedList using simple for-each loop ===");
        for (String one: humanSpecies) {
            System.out.println(one);
        }
    }
}
