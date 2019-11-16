package com.telerikacademy.func;

import com.telerikacademy.models.Bookmark;
import com.telerikacademy.models.BookmarkListFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        testFunction();
//        testBiFunction();
//        testPredicate();
//        testConsumer();
//        testSupplier();
    }

    public static void testFunction() {
        // convert centigrade to fahrenheit
        // fahrenheit = (centigrade * 9 / 5) + 32;

        // String to an integer

        // Square of integer

        // Use UnaryOperator


        // tests
//        int centigrade = 220;
//        System.out.println("Centigrade to Fahrenheit: " + );
//        System.out.println(" String to Int: " + );
//        System.out.println(" Square 10: " + );
//        System.out.println(" Unary Square 10: " +);
    }

    public static void testBiFunction() {
        // Format Price Label

        // Get Bookmark with bigger rating

        // Use BinaryOperator

        // tests
//        System.out.println(formatPrice.apply(4.50, "milk"));
//        List<Bookmark> bookmarks = BookmarkListFactory.getBookmarkList();
//        Bookmark b1 = bookmarks.get(2);
//        Bookmark b2 = bookmarks.get(3);
//        System.out.println("More popular bookmark from '" + b1.getTitle()
//                + "' and '" + b2.getTitle() + "' is " + getMorePopularBookmark.apply(b1, b2));
    }

    public static void testPredicate() {
        // Lambda to check an empty string

        // Check if string is empty
//        String s1 = "";
//        String s2 = "Hello!";
//        System.out.println(String.format("s1 is %s.", emptyStringChecker.test(s1) ? "empty" : "not empty"));
//        System.out.println(String.format("s2 is %s.", emptyStringChecker.test(s2) ? "empty" : "not empty"));
    }

    public static void testConsumer() {
        // Consumer to display a number


        // Consumer to multiply by 2 every integer of a list


        // Consumer to display a list of numbers


        // Create List of Integers
        List<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(1);
        list.add(3);

        // Implement display using accept()

        // Implement modify using accept()

        // Implement displayList using accept()
    }

    public static void testSupplier() {
        // This function returns a random value.


        // Print the random value using get()

    }
}
