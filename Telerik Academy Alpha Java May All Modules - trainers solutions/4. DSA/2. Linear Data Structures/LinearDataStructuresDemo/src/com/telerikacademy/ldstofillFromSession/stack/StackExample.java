package com.telerikacademy.ldstofillFromSession.stack;

import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        // Creating a Stack stackOfCards


        // Pushing new items to the Stack


        // System.out.println("Stack => " + stackOfCards);
        System.out.println();

        // Popping items from the Stack
        // Throws EmptyStackException if the stack is empty
        String cardAtTop = null;
        System.out.println("Stack.pop() => " + cardAtTop);
       // System.out.println("Current Stack => " + stackOfCards);
        System.out.println();

        // Get the item at the top of the stack without removing it

        System.out.println("Stack.peek() => " + cardAtTop);
      //  System.out.println("Current Stack => " + stackOfCards);

    }
}
