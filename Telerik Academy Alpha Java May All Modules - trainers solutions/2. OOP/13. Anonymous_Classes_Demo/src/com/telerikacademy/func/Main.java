package com.telerikacademy.func;

import com.telerikacademy.models.Bookmark;
import com.telerikacademy.models.BookmarkListFactory;
import com.telerikacademy.models.MovieBookmark;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        List<Bookmark> recommendedItems = BookmarkListFactory.getBookmarkList();

        // Use anonymous class to implement comparator in order to sort items
        // Use constant for anonymous class

        // Test that lambda works only with functional interfaces
        // 1. Create functional interface, method that accepts argument of its type(go(Test t))
        // and calls interface's method.
        // 2. Use it to create lambda
        // 3. Add another not implemented method
        // 4. Add default method
        // 5. override toString()
        // 6. Change the interface to abstract class with one abstract method

    }
}
