package com.telerikacademy.cache;

import com.telerikacademy.models.Bookmark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Cache recommendedItems = new Cache(5);

        // Create bookmarks and cache them
        Bookmark item1 = new Bookmark();
        item1.setId(2000);
        item1.setRating(4.0);
        item1.setTitle("Use Final Liberally");

        Bookmark item2 = new Bookmark();
        item2.setId(2001);
        item2.setRating(4.5);
        item2.setTitle("How do I import a pre-existing Java project into Eclipse and get up and running?");

        Bookmark item3 = new Bookmark();
        item3.setId(2002);
        item3.setRating(5.0);
        item3.setTitle("Interface vs Abstract Class");

        Bookmark item4 = new Bookmark();
        item4.setId(2003);
        item4.setRating(4.0);
        item4.setTitle("NIO tutorial by Greg Travis");

        Bookmark item5 = new Bookmark();
        item5.setId(2004);
        item5.setRating(3.5);
        item5.setTitle("Virtual Hosting and Tomcat");

        recommendedItems.add(item1);
        recommendedItems.add(item2);
        recommendedItems.add(item3);
        recommendedItems.add(item4);
        recommendedItems.add(item5);

        // In Cache class
        // Use anonymous class to implement comparator in order to sort items

        // Use lambda to implement comparator in order to sort items

        // Use lambda expression to implement comparator in order to sort items
        // Compiler assumes the type of the parameters based on declaration
        // Get to know ternary operator

        // Create functional interface PopularBookmark with getMostPopular(Bookmark[] items) method.
        // Implement getMostPopular in Cache


        System.out.println("********* Sorted by entrance ...");
        recommendedItems.print();
        System.out.println("********* Sorted by rating ...");
        recommendedItems.sort();
        recommendedItems.print();
    }
}
