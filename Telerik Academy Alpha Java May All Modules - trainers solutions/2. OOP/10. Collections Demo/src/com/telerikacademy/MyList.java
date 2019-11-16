package com.telerikacademy;

import java.util.Objects;

public interface MyList<E> extends Iterable<E> {
    void add(E element);

    E get(int index);

    void set(int index, E value);

    int find(E value);
}
