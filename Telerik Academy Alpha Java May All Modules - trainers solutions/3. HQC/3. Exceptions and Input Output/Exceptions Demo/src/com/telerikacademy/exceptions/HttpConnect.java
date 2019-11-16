package com.telerikacademy.exceptions;

import java.io.FileNotFoundException;

public class HttpConnect {

    // create an exception situation - FileNotFoundException;
    // see how the compiler behaves
    // use try-catch
    // use throws

    // add finally in send and share methods and see what is printed in different cases
    // happy case
    // bad case with exception handling
    // bad case with re-throwing it back to main

    // run code to test diff situations - handling the exception in different cases

    public static void send(int hasNetwork, String data, String webAddress) throws FileNotFoundException {
        System.out.println("\nInside send ...");

        if (hasNetwork > 0) {
            System.out.println(String.format("\nExecuting Web Request to %s, sending data '%s'", webAddress, data));
        } else {

            throw new FileNotFoundException();

//            try {
//                throw new FileNotFoundException();
//            } catch (FileNotFoundException e) {
//                // do some smart work & handle
//                e.printStackTrace();
//            }
        }

        System.out.println("\nEnd of send ...");
    }
}
