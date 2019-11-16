package com.telerikacademy;

import java.util.ArrayList;
import java.util.List;

public class StackImpl<T> implements Stack<T> {
    private List<T> elements;

    public StackImpl() {
        this.elements = new ArrayList<>();
    }

    public StackImpl(Iterable<T> elements) {
        this.elements = new ArrayList<>();
        for (T elem : elements
        ) {
            this.elements.add(elem);
        }
    }

    @Override
    public void push(T elem) {
        this.elements.add(elem);
    }

    @Override
    public T pop() {
        if (size() == 0)
            throw new IllegalArgumentException();
        return this.elements.remove(size() - 1);
    }

    @Override
    public T peek() {
        if (size() == 0)
            throw new IllegalArgumentException();
        return this.elements.get(size() - 1);
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public boolean isEmpty() {
        return !(this.size() > 0);
    }
}
