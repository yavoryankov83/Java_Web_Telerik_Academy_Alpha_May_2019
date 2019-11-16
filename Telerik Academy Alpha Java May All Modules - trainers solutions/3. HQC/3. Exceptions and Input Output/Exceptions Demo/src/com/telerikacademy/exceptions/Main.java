package com.telerikacademy.exceptions;

import javafx.beans.binding.When;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        System.out.println("\nInside main ...");

//        try {
//            executeSomeWebRequest();
//            System.out.println("Some code after the exception");
//        } catch (RuntimeException ex) {
//            System.out.println("Fill out your name");
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//        }




        // Test Checked vs. Unchecked with inheritance
        // 1: Test if method throws an unchecked exception while base class does not trow at all
        // 2: Base class doesn’t throw any exception but child class throws an checked exception - let color throw IOException
        // 3: When base class and child class both throws a checked exception
        // 4: When child class method is throwing parent checked exception compared to the same method of base class
        // color in building throws IOException, but color in room - Exception

        Building obj = new Room();
        obj.color();

        System.out.println("\nEnd of main ...");
    }

    private static void executeSomeWebRequest() throws FileNotFoundException {
        System.out.println("\nInside executeSomeWebRequest ...");
        try {
            HttpConnect.send(0, "dog", "http://google.com");
        } catch (IllegalArgumentException ex) {
            System.out.println("In the catch");
            throw new RuntimeException("Status Code: 500; Bad Request");
        } finally {
            System.out.println("In finally");
        }

        // re-throw the exception that comes from the method

        // add another exceptional situation
        // add it in throws
        // add multiple catch blocks

        // exceptions can be used polymorphically - no need to throws both can be only
        // the most abstract one. Compiler is keeping you safe
        // multiple catches - if you have specific handling for different situations & start from most concrete one

        // demonstrate unchecked exception - wrong argument - compiler is okay
        // good practice - document it

        // show method overriding

        System.out.println("\nEnd of executeSomeWebRequest ...");
    }


}
