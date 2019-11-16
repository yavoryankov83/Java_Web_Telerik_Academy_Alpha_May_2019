package com.telerikacademy.func;

import com.telerikacademy.models.Bookmark;
import com.telerikacademy.models.BookmarkListFactory;

import java.net.MalformedURLException;
import java.util.*;
import java.util.function.*;

// Test if this is abstract class with abstract method
interface Test {
    void go();

    // Test that lambda works only with functional interfaces
    //void stop();

    // Every class we know implicitly inherits from the Object class so that implementation is already there.
    //String toString();
}

public class MainFull {
    public static final Comparator<Bookmark> SORT_BOOKMARKS_BY_RATING = new Comparator<Bookmark>() {
        @Override
        public int compare(Bookmark o1, Bookmark o2) {
            if (o1.getRating() == o2.getRating()) {
                return o1.getTitle().compareTo(o2.getTitle());
            } else {
                return o1.getRating() < o2.getRating() ? 1 : -1;
            }
        }
    };

    public static void main(String[] args) {
        List<Bookmark> recommendedItems = BookmarkListFactory.getBookmarkList();

        for (Bookmark b : recommendedItems) {
            System.out.println(b);
        }

        // Use anonymous class to implement comparator in order to sort items
        recommendedItems.sort(new Comparator<Bookmark>() {

            @Override
            public int compare(Bookmark o1, Bookmark o2) {
                return o1.getRating() < o2.getRating() ? 1 : -1;
            }
        });

        // Use constant for anonymous class
        recommendedItems.sort(SORT_BOOKMARKS_BY_RATING);

        System.out.println("\nSorted by rating:");
        for (Bookmark b : recommendedItems) {
            System.out.println(b);
        }

        // Use lambda to implement comparator in order to sort items
        recommendedItems.sort((Bookmark o1, Bookmark o2) -> {
            return o1.getRating() < o2.getRating() ? 1 : -1;
        });

        // Use lambda expression to implement comparator in order to sort items
        // Compiler assumes the type of the parameters based on declaration
        recommendedItems.sort((o1, o2) -> o1.getRating() < o2.getRating() ? 1 : -1);

        // Test that lambda works only with functional interfaces
        go(() -> System.out.println("go go go!"));
    }

    public static void go(Test t) {
        t.go();
    }
}
