package com.telerikacademy;

public interface MyList<E> extends Iterable<E> {
    int size();

    void add(E element);

    E get(int index);

    void set(int index, E value);

    int find(E value);
}
