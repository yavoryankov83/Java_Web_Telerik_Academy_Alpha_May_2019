package com.telerikacademy;

import java.util.Arrays;
import java.util.Iterator;

public class MyArrayList<E> implements MyList<E> {
    private Object[] data;
    private int size;

    public MyArrayList() {
        data = new Object[4];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E element) {
        if (size == data.length) {
            Object[] newData = Arrays.copyOf(data, data.length * 2);
            data = newData;
        }
        data[size] = element;
        size++;
    }

    @Override
    public E get(int index) {
        checkIndexIsValid(index);
        return (E) data[index];
    }

    @Override
    public void set(int index, E value) {
        checkIndexIsValid(index);
        data[index] = value;
    }

    @Override
    public int find(E value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndexIsValid(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {
        int currentPosition;

        MyIterator() {
            currentPosition = 0;
        }

        @Override
        public boolean hasNext() {
            return currentPosition < size;
        }

        @Override
        public E next() {
            //return data[currentPosition++];

            E result = (E) data[currentPosition];
            currentPosition++;
            return result;
        }
    }
}
