package com.telerikacademy.streams;

import com.sun.jndi.toolkit.url.Uri;
import com.telerikacademy.models.Bookmark;
import com.telerikacademy.models.BookmarkListFactory;
import com.telerikacademy.models.Review;

import javax.xml.stream.events.Characters;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainFull {
    public static void main(String[] args) {
        List<Bookmark> recommendedItems = BookmarkListFactory.getBookmarkList();

        // Iteration
        // Substitute for, for-each and while loops.
        // Allows concentrating on operation’s logic, but not on the iteration over the sequence of elements.
        for (Bookmark b : recommendedItems) {
            if (b.getTitle().contains("Java")) {
                System.out.println("Yes!");
                break;
            }
        }
        // TODO use stream, anyMatch
        boolean hasElement = recommendedItems
                .stream()
                .anyMatch(element -> element.getTitle().contains("Java"));

        System.out.println(hasElement ? "Yes!": "");

        // Filtering
        // The filter() method allows us to pick stream of elements which satisfy a predicate
        // Get all bookmarks that contain Java in their title
        // TODO use stream, filter
        Stream<Bookmark> bookmarkStream = recommendedItems
                .stream()
                .filter(element -> element.getTitle().contains("Java"));
        bookmarkStream.forEach(element -> System.out.println(element));

        // Mapping
        // To convert elements of a Stream by applying a special function to them and to collect these new elements into a Stream,
        // we can use the map() method.
        // Get rounded up ratings for all bookmarks

        // TODO use stream, map
        Stream<Double> doubleStream = recommendedItems
                .stream()
                .map(element -> Math.ceil(element.getRating()));
        doubleStream.forEach(element -> System.out.println(element));

        // Get all reviews that are waiting for approval
        // TODO use stream, flatMap
        Stream<Review> reviewStream = recommendedItems
                .stream()
                .flatMap(element -> element.getReviews()
                        .stream()
                        .filter(review -> !review.isApproved()));
        reviewStream.forEach(element -> System.out.println(element));

        // Matching
        // Stream API gives a handy set of instruments to validate elements of a sequence according to some predicate.
        // To do this one of the following methods can be used: anyMatch(), allMatch(), noneMatch().
        // Their names are self-explaining. Those are terminal operations which return a boolean.
        // TODO use stream, anyMatch(), allMatch(), noneMatch()
        // TODO test if there is item containing in its title "Java"
        // TODO test if all items has rating bigger than 2.0
        // TODO test if any item has rating lower than 2.0
        hasElement = recommendedItems
                .stream()
                .anyMatch(element -> element.getTitle().contains("Java"));

        hasElement = recommendedItems
                .stream()
                .allMatch(element -> element.getRating() > 2.0);

        hasElement = recommendedItems
                .stream()
                .noneMatch(element -> element.getRating() == 2.0);

        // Reduction
        // Stream API allows reducing a sequence of elements to some value according to a specified function with the help of the reduce() method.
        // This method takes two parameters: first – start value, second – an accumulator function.
        // TODO use stream, reduce - calculate sum
        List<Integer> integers = Arrays.asList(1, 1, 1);
        Integer reduced = integers.stream().reduce(23, (a, b) -> a + b);
        System.out.println(reduced);

        // Collecting
        // The reduction can also be provided by the collect() method of type Stream.
        // This operation is very handy in case of converting a stream to a Collection or a Map and representing a stream in form of a single string.
        // TODO use stream, Collectors - get bookmarks list with all titles uppercase

        List<String> resultList = recommendedItems
                .stream()
                .map(element -> element.getTitle().toUpperCase())
                .collect(Collectors.toList());
        resultList.forEach(System.out::println);

    }
}
