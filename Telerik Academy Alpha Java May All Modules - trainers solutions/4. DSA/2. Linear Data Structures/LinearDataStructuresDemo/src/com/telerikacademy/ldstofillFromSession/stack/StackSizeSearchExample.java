package com.telerikacademy.ldstofillFromSession.stack;

import java.util.Stack;

public class StackSizeSearchExample {
    public static void main(String[] args) {

        // Creating a Stack stackOfCards
        Stack<String> stackOfCards = new Stack<>();

        stackOfCards.push("Jack");
        stackOfCards.push("Queen");
        stackOfCards.push("King");
        stackOfCards.push("Ace");

        System.out.println("Stack : " + stackOfCards);

        // Check if the Stack is empty
        System.out.println("Is Stack empty? : " + stackOfCards.isEmpty());

        // Find the size of Stack
        System.out.println("Size of Stack : " + stackOfCards.size());

        // Search for an element
        // The search() method returns the 1-based position of the element from the top of the stack
        // It returns -1 if the element was not found in the stack
        int indexOfQueen = 0;
//        System.out.println(indexOfQueen);
//        if (indexOfQueen != -1) {
//            System.out.println("Found the element \"Queen\" at position : " + indexOfQueen);
//        } else {
//            System.out.println("Element not found");
//        }
    }
}
