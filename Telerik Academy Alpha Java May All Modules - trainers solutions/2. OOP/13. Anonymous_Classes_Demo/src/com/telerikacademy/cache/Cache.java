package com.telerikacademy.cache;

import com.telerikacademy.models.Bookmark;

import java.util.Arrays;
import java.util.Comparator;

public class Cache {
    private Bookmark[] items;
    //keep track of the next item that is being added
    private int next = 0;

    public Cache(int size) {
        items = new Bookmark[size];
    }

    public void add(Bookmark item) {
        if (next < items.length)
            items[next++] = item;
    }

    public void print() {
        for (Bookmark bookmark : items) {
            System.out.println(bookmark);
        }
    }

    //TODO Use anonymous class to implement comparator in order to sort items
    //TODO Use lambda to implement comparator in order to sort items
    //TODO Use lambda with no parameter type syntax to implement comparator in order to sort items
    //TODO Use lambda expression to implement comparator in order to sort items

    public void sort() {
        //Use Arrays.sort();
    }

    public Bookmark getMostPopular(PopularBookmark helper) {
        return helper.getMostPopular(items);
    }

}
