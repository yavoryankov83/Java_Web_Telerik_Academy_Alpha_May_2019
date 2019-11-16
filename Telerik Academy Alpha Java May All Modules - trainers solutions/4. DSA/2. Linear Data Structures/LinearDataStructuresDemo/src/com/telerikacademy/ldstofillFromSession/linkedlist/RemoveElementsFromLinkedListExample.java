package com.telerikacademy.ldstofillFromSession.linkedlist;

import java.util.LinkedList;

public class RemoveElementsFromLinkedListExample {
    public static void main(String[] args) {

        // Create LinkedList with programing languages
        LinkedList<String> programmingLanguages = new LinkedList<>();

        programmingLanguages.add("Assembly");
        programmingLanguages.add("Fortran");
        programmingLanguages.add("Pascal");
        programmingLanguages.add("C");
        programmingLanguages.add("C++");
        programmingLanguages.add("Java");
        programmingLanguages.add("C#");
        programmingLanguages.add("Kotlin");

        String element = null;
        // Remove the first element in the LinkedList
        //  method removeFirst Throws NoSuchElementException if the LinkedList is empty

        element = programmingLanguages.removeFirst();
        System.out.println("Removed the first element " + element + " => " + programmingLanguages);

        // Remove the last element in the LinkedList
        // Throws NoSuchElementException if the LinkedList is empty
        element = programmingLanguages.removeLast();
        System.out.println("Removed the last element " + element + " => " + programmingLanguages);

        // Remove the first occurrence of the specified element from the LinkedList
        // removed returns whether the element is removed

        boolean hasElem = programmingLanguages.removeFirstOccurrence("C#");
        hasElem = programmingLanguages.removeFirstOccurrence("C#");

        if (hasElem) {
            System.out.println("Removed C# => " + programmingLanguages);
        } else {
            System.out.println("No such element");
        }

        // Remove all the elements that satisfy the given predicate - removeIf
        hasElem = programmingLanguages.removeIf(lang -> lang.startsWith("C"));
        System.out.println("Removed elements starting with C => " + programmingLanguages);

        // Clear the LinkedList by removing all elements
        programmingLanguages.clear();
        System.out.println("Cleared the LinkedList => " + programmingLanguages);
    }
}
