package com.telerikacademy.exceptions;

public class JavaExceptionExample {
    public static void main(String[] args) {
        try {
            // code that may raise exception

            // System.out.println("Rest of code in try ...");
        } catch (ArithmeticException ex) {

        }

        //  Handle the exception using the parent class exception
        // Add finally - show handled and unhandled situations

        System.out.println("Rest of code in main ...");
    }
}
