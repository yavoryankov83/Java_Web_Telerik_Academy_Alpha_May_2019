package com.telerikacademy;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedListImpl<T> implements DoublyLinkedList<T> {

    private DoubleNode<T> head;
    private DoubleNode<T> tail;
    private int count = 0;

    public DoublyLinkedListImpl() {
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param iterable - the collection whose elements are to be placed into this list.
     */
    public DoublyLinkedListImpl(Iterable<T> iterable) {
        iterable.forEach(this::addLast);
    }

    /**
     * Returns the first element in the DoublyLinked list.
     *
     * @return the first element in the list.
     */
    @Override
    public DoubleNode getHead() {
        return head;
    }

    /**
     * Returns the last element in the DoublyLinked list.
     *
     * @return the last element in the list.
     */
    @Override
    public DoubleNode getTail() {
        return tail;
    }

    /**
     * Returns the size of the DoublyLinked list.
     *
     * @return the size of the list.
     */
    @Override
    public int getCount() {
        return count;
    }

    /**
     * Adds the specified element to the end of the list.
     *
     * @param value - the value of the DoubleNode.
     */
    @Override
    public void addLast(T value) {

        DoubleNode<T> oldTail = tail;
        DoubleNode<T> newNode = new DoubleNode<>(oldTail, value, null);
        tail = newNode;

        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }

        count++;

    }

    /**
     * Adds the specified element to the beginning of the list.
     *
     * @param value - the value of the new DoubleNode.
     */
    @Override
    public void addFirst(T value) {

        DoubleNode<T> oldHead = head;
        DoubleNode<T> newNode = new DoubleNode<>(null, value, oldHead);

        head = newNode;

        if (oldHead == null) {
            tail = newNode;
        } else {
            oldHead.prev = newNode;
        }

        count++;

    }

    /**
     * Inserts a new non-null DoubleNode before node.
     *
     * @param node  - the DoubleNode before which a new one has to be inserted.
     * @param value - the value of the new DoubleNode.
     */
    @Override
    public void insertBefore(DoubleNode node, T value) {

        DoubleNode prev = node.prev;
        DoubleNode<T> newNode = new DoubleNode<>(prev, value, node);

        node.prev = newNode;

        if (prev == null) {
            head = newNode;
        } else {
            prev.next = newNode;
        }

        count++;

    }

    /**
     * Inserts a new non-null DoubleNode after node.
     *
     * @param node  - the DoubleNode after which a new one has to be inserted.
     * @param value - the value of the new DoubleNode.
     */
    @Override
    public void insertAfter(DoubleNode node, T value) {

        DoubleNode next = node.next;
        DoubleNode newNode = new DoubleNode<>(node, value, null);

        newNode.next = next;
        node.next = newNode;

        if (next != null) {
            next.prev = newNode;
        }

        count++;
    }

    /**
     * Removes the first element in the list.
     *
     * @return the value of the removed node.
     * @throws NullPointerException if the list is empty.
     */
    @Override
    public T removeFirst() {

        DoubleNode<T> oldFirst = head;

        if (oldFirst == null)
            throw new NullPointerException();

        DoubleNode<T> newFirst = head.next;
        head = newFirst;

        if (newFirst == null) {
            tail = null;
        } else {
            newFirst.prev = null;
        }

        count--;

        return oldFirst.getValue();
    }

    /**
     * Removes the last element in the list.
     *
     * @return the value of the removed node.
     * @throws NullPointerException if the list is empty.
     */
    @Override
    public T removeLast() {

        DoubleNode<T> oldLast = tail;

        if (oldLast == null)
            throw new NullPointerException();

        DoubleNode<T> newLast = tail.prev;
        tail = newLast;

        if (newLast == null) {
            head = null;
        } else {
            newLast.prev = null;
        }

        count--;

        return oldLast.getValue();
    }

    /**
     * Finds the first occurrence of the node in the list with value equal to value.
     *
     * @param value the wanted value.
     * @return the removed node if the list is not empty.
     */
    @Override
    public DoubleNode find(T value) {

        if (isEmpty()) {
            return null;
        }

        DoubleNode<T> current = head;

        while (current != null) {
            if (current.getValue().equals(value)) return current;
            else current = current.next;
        }

        return null;
    }

    /**
     * Creates a instance of {@link Iterator}.
     *
     * @return instance of {@link Iterator}
     */
    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    private boolean isEmpty(){
        return count == 0;
    }



    private class IteratorImpl implements Iterator<T> {

        private DoubleNode<T> current = head;

        IteratorImpl() {
        }

        @Override
        public boolean hasNext() {
            return current != null && current.next != null;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();

            current = current.next;

            return current.getValue();
        }
    }

}
