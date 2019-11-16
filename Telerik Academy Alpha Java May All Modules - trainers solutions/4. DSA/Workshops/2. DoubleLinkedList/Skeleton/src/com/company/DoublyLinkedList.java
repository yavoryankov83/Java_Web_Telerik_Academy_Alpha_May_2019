package com.company;


public interface DoublyLinkedList<T> extends Iterable<T> {
    DoubleNode getHead();
    DoubleNode getTail();
    int getCount();
    void addLast(T value);
    void addFirst(T value);
    void insertBefore(DoubleNode node, T value);
    void insertAfter(DoubleNode node, T value);
    T removeFirst();
    T removeLast();
    DoubleNode find(T value);
}
