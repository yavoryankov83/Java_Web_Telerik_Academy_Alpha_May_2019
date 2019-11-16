package com.company;

public class DoubleNode<T> {
    private T value;
    public DoubleNode<T> prev;
    public DoubleNode<T> next;

    public DoubleNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public DoubleNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DoubleNode<T> prev) {
        this.prev = prev;
    }

    public DoubleNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }
}