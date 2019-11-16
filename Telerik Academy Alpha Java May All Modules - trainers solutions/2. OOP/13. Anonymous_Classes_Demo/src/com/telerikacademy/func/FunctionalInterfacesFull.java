package com.telerikacademy.func;

import com.telerikacademy.models.Bookmark;
import com.telerikacademy.models.BookmarkListFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class FunctionalInterfacesFull {
    public static void main(String[] args) {
        testFunction();
        testBiFunction();
        testPredicate();
        testConsumer();
        testSupplier();
    }

    public static void testFunction() {
        // convert centigrade to fahrenheit
        // fahrenheit = (centigrade * 9 / 5) + 32;
        Function<Integer, Double> centigradeToFahrenheitInt = x -> new Double((x * 9 / 5) + 32);

        // String to an integer
        Function<String, Integer> stringToInt = x -> Integer.valueOf(x);

        // Square of integer
        Function<Integer, Integer> squareInteger = x -> x * x;
        // Use UnaryOperator
        UnaryOperator<Integer> unarySquareIntegers = x -> x * x;

        // tests
        int centigrade = 10;
        System.out.println("Centigrade to Fahrenheit: " + centigradeToFahrenheitInt.apply(centigrade));
        System.out.println(" String to Int: " + stringToInt.apply("4"));
        System.out.println(" Square 10: " + squareInteger.apply(10));
        System.out.println(" Unary Square 10: " + unarySquareIntegers.apply(12));
    }

    public static void testBiFunction() {
        // Format Price Label
        BiFunction<Double, String, String> formatPrice =
                (price, item) -> String.format("%s costs $%.2f", item, price);

        // Get Bookmark with bigger rating
        BiFunction<Bookmark, Bookmark, Bookmark> getMorePopularBookmark =
                (b1, b2) -> b1.getRating() > b2.getRating() ? b1 : b2;
        // Use BinaryOperator
        BinaryOperator<Bookmark> getMorePopularBookmarkOperator =
                (b1, b2) -> b1.getRating() > b2.getRating() ? b1 : b2;


        // tests
        System.out.println(formatPrice.apply(4.50, "milk"));
        List<Bookmark> bookmarks = BookmarkListFactory.getBookmarkList();
        Bookmark b1 = bookmarks.get(2);
        Bookmark b2 = bookmarks.get(3);
        System.out.println("More popular bookmark from '" + b1.getTitle()
                + "' and '" + b2.getTitle() + "' is " + getMorePopularBookmark.apply(b1, b2));
    }

    public static void testPredicate() {
        // Lambda to check an empty string
        Predicate<String> emptyStringChecker = s -> s.isEmpty();

        // Check if string is empty
        String s1 = "";
        String s2 = "Hello!";
        System.out.println(String.format("s1 is %s.", emptyStringChecker.test(s1) ? "empty" : "not empty"));
        System.out.println(String.format("s2 is %s.", emptyStringChecker.test(s2) ? "empty" : "not empty"));

    }

    public static void testConsumer() {
        // Consumer to display a number
        Consumer<Integer> display = a -> System.out.println(a);

        // Consumer to multiply by 2 every integer of a list
        Consumer<List<Integer>> modify = list -> {
            for (int i = 0; i < list.size(); i++)
                list.set(i, 2 * list.get(i));
        };

        // Consumer to display a list of numbers
        Consumer<List<Integer>> displayList = list -> {
            for (Integer item : list) {
                System.out.print(item + " ");
            }
        };

        List<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(1);
        list.add(3);

        // Implement display using accept()
        display.accept(10);

        // Implement modify using accept()
        modify.accept(list);

        // Implement displayList using accept()
        displayList.accept(list);
    }

    public static void testSupplier() {
        // This function returns a random value.
        Supplier<Double> randomValue = () -> Math.random();

        // Print the random value using get()
        System.out.println(randomValue.get());
    }
}
