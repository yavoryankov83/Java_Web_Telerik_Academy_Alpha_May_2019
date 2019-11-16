package com.telerikacademy;

import java.util.ArrayList;
import java.util.List;

public class QueueImpl<T> implements Queue<T> {
    private List<T> elements;

    public QueueImpl() {
        this.elements = new ArrayList<>();
    }

    public QueueImpl(Iterable<T> elements) {
        this.elements = new ArrayList<>();
        for (T elem : elements
        ) {
            this.elements.add(elem);
        }
    }

    @Override
    public void offer(T elem) {
        this.elements.add(elem);
    }

    @Override
    public T poll() {
        if (size() == 0)
            throw new IllegalArgumentException("Queue is empty");
        return this.elements.remove(0);
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0)
            return true;
        return false;
    }

    @Override
    public T peek() {
        if (size() == 0)
            throw new IllegalArgumentException("Queue is empty");
        return this.elements.get(0);
    }

    @Override
    public int size() {
        return this.elements.size();
    }


}
