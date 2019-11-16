package com.company.tests;


import com.company.DoubleNode;
import com.company.DoublyLinkedListImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class LinkedListTest {

    private DoublyLinkedListImpl<Integer> testList;

    @Before
    public void before() {
        // Arrange
        testList = new DoublyLinkedListImpl<>();
    }
    @Test
    public void addFirst_should_updateCount() {
        // Act
        testList.addFirst(1);
        testList.addFirst(1);
        testList.addFirst(1);
        // Assert
        Assert.assertEquals(testList.getCount(),3);
    }

    @Test
    public void addFirst_should_updateHead_whenListEmpty() {
        // Act
        testList.addFirst(5);

        // Assert
        Assert.assertEquals(5, (int)testList.getHead().getValue());
    }

    @Test
    public void addFirst_should_updateHead_whenListNotEmpty() {
        // Arrange
        testList.addFirst(5);

        // Act
        testList.addFirst(10);

        // Assert
        Assert.assertEquals(10, (int)testList.getHead().getValue());
    }

    @Test
    public void addFirst_should_maintainCorrectOrder() {
        // Arrange & Act
        List<Integer> values = new ArrayList<>(Arrays.asList(5,4,3,2,1));

        for (int value:
                values) {
            testList.addFirst(value);
        }
        for (Integer num:testList
        ) {
            System.out.println(num);
        };

        List<Integer> valuesTOCompareWith = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        compareTestArrayWith(valuesTOCompareWith);

    }

    @Test
    public void addLast_should_updateCount() {
        // Arrange && Act
        testList.addLast(5);
        testList.addLast(5);
        // Assert
        Assert.assertEquals(2, testList.getCount());
    }

    @Test
    public void addLast_should_updateTail_whenListEmpty() {
        // Act
        testList.addLast(5);

        // Assert
        Assert.assertEquals(5, (int)testList.getTail().getValue());
    }

    @Test
    public void addLast_should_updateTail_whenListNotEmpty() {
        // Arrange
        testList.addLast(5);

        // Act
        testList.addLast(10);

        // Assert
        Assert.assertEquals(10, (int)testList.getTail().getValue());
    }

    @Test
    public void addLast_should_maintainCorrectOrder() {
        // Arrange & Act
        List<Integer> values = new ArrayList<>(Arrays.asList(1,2,3,4,5));

        for (int value:
                values) {
            testList.addLast(value);
        }
        // Assert
        compareTestArrayWith(values);


    }

    @Test
    public void find_should_returnNode_withOneElementInList() {
        // Arrange & Act
        testList.addLast(3);
        DoubleNode node = testList.find(3);

        // Assert
        Assert.assertEquals( 3,node.getValue());
    }

    @Test
    public void find_should_returnNode_withManyElementsInList() {
        // Arrange
        testList.addLast(5);
        testList.addLast(4);
        testList.addLast(3);
        testList.addLast(7);

        // Act
        DoubleNode node = testList.find(3);

        // Assert
        Assert.assertEquals( 3, node.getValue());
    }

    @Test
    public void find_should_returnNode_whenNodeIsLast() {
        // Arrange
        testList.addLast(4);
        testList.addLast(43);
        testList.addLast(34);
        testList.addLast(42);

        // Act
        DoubleNode node = testList.find(42);

        // Assert
        Assert.assertEquals( 42, node.getValue());
    }

    @Test
    public void find_should_returnNull_whenNoElements() {
        // Act
        DoubleNode node = testList.find(42);

        // Assert
        Assert.assertNull( node);
    }

    @Test
    public void find_should_returnNull_whenElementsInArray() {
        // Act
        testList.addLast(43);
        testList.addLast(34);
        testList.addLast(42);

        // Arrange
        DoubleNode node = testList.find(432);

        // Assert
        Assert.assertNull( node);
    }

    @Test
    public void insertAfter_should_insertNode_InMiddle() {
        // Arrange
        List<Integer> expected = Arrays.asList(1,2,3,4,5);
        testList = new DoublyLinkedListImpl<>(Arrays.asList(1,2,4,5));

        // Act
        DoubleNode targetNode = testList.find(2);
        testList.insertAfter(targetNode, 3);

        // Assert
        compareTestArrayWith(expected);

    }

    @Test
    public void insertAfter_should_insertNode_InEnd() {
        // Arrange
        List<Integer> expected = Arrays.asList(1,2,3,4,5);
        testList = new DoublyLinkedListImpl<>(Arrays.asList(1,2,3,4));

        // Act
        DoubleNode targetNode = testList.find(4);
        testList.insertAfter(targetNode, 5);

        // Assert
        compareTestArrayWith(expected);

    }

    @Test
    public void insertAfter_should_insertNode_AfterFreshInsert() {
        // Arrange
        List<Integer> expected = Arrays.asList(1,2,3,4,5);
        testList = new DoublyLinkedListImpl<>(Arrays.asList(1,4,5));

        // Act
        DoubleNode targetNode = testList.find(1);
        testList.insertAfter(targetNode, 2);
        DoubleNode freshNode = testList.find(2);
        testList.insertAfter(freshNode, 3);
        // Assert
        compareTestArrayWith(expected);

    }

    @Test
    public void insertAfter_should_updateCount() {
        // Arrange
        testList = new DoublyLinkedListImpl<>(Arrays.asList(1,4,5));

        // Act
        DoubleNode targetNode = testList.find(1);
        testList.insertAfter(targetNode, 2);
        // Assert
        Assert.assertEquals(4,testList.getCount());

    }

    @Test(expected = NullPointerException.class)
    public void insertAfter_should_throwNullException_whenNullPassed() {
        testList.insertAfter(null, 5);
    }


    @Test
    public void insertBefore_should_insertNode_InMiddle() {
        // Arrange
        List<Integer> expected = Arrays.asList(1,2,3,4,5);
        testList = new DoublyLinkedListImpl<>(Arrays.asList(1,2,4,5));

        // Act
        DoubleNode targetNode = testList.find(4);
        testList.insertBefore(targetNode, 3);

        // Assert
        compareTestArrayWith(expected);

    }

    @Test
    public void insertBefore_should_insertNode_atBeginning() {
        // Arrange
        List<Integer> expected = Arrays.asList(1,2,3,4,5);
        testList = new DoublyLinkedListImpl<>(Arrays.asList(2,3,4,5));

        // Act
        DoubleNode targetNode = testList.find(2);
        testList.insertBefore(targetNode, 1);

        // Assert
        compareTestArrayWith(expected);

    }

    @Test
    public void insertBefore_should_insertNode_AfterFreshInsert() {
        // Arrange
        List<Integer> expected = Arrays.asList(1,2,3,4,5);
        testList = new DoublyLinkedListImpl<>(Arrays.asList(1,4,5));

        // Act
        DoubleNode targetNode = testList.find(4);
        testList.insertBefore(targetNode, 3);
        DoubleNode freshNode = testList.find(3);
        testList.insertBefore(freshNode, 2);
        // Assert
        compareTestArrayWith(expected);

    }

    @Test
    public void insertBefore_should_updateCount() {
        // Arrange
        testList = new DoublyLinkedListImpl<>(Arrays.asList(1,4,5));

        // Act
        DoubleNode targetNode = testList.find(1);
        testList.insertBefore(targetNode, 2);
        // Assert
        Assert.assertEquals(4,testList.getCount());

    }

    @Test
    public void removeFirst_should_decreaseCount(){
        // Arrange
        testList.addFirst(1);
        testList.addFirst(1);

        // Act
        testList.removeFirst();

        // Assert
        Assert.assertEquals(1, testList.getCount());
    }
    @Test
    public void removeFirst_should_returnCorrectValue__withMultipleElementsInList(){
        // Arrange
        testList.addFirst(1);
        testList.addFirst(5);
        testList.addFirst(7);
        testList.addLast(7);
        // Act
        int val = testList.removeFirst();

        // Assert
        Assert.assertEquals(7, val);
    }

    @Test
    public void removeFirst_should_returnCorrectValue_withSingleElementsInList(){
        // Arrange
        testList.addFirst(1);
        // Act
        int val = testList.removeFirst();

        // Assert
        Assert.assertEquals(1, val);
    }


    @Test(expected = NullPointerException.class)
    public void removeFirst_should_throwNullException_whenListEmpty() {
        testList.removeFirst();
    }

    @Test
    public void removeLast_should_decreaseCount(){
        // Arrange
        testList.addFirst(1);
        testList.addFirst(1);

        // Act
        testList.removeLast();

        // Assert
        Assert.assertEquals(1, testList.getCount());
    }
    @Test
    public void removeLast_should_returnCorrectValue__withMultipleElementsInList(){
        // Arrange
        testList.addFirst(1);
        testList.addFirst(5);
        testList.addFirst(7);
        // Act
        int val = testList.removeLast();

        // Assert
        Assert.assertEquals(1, val);
    }

    @Test
    public void removeLast_should_returnCorrectValue_withSingleElementsInList(){
        // Arrange
        testList.addFirst(1);
        // Act
        int val = testList.removeLast();

        // Assert
        Assert.assertEquals(1, val);
    }


    @Test(expected = NullPointerException.class)
    public void removeLast_should_throwNullException_whenListEmpty() {
        testList.removeLast();
    }


    private void compareTestArrayWith(List<Integer> values) {
        DoubleNode currentNode = testList.getHead();


        int index = 0;
        while(currentNode.next != null) {
            Assert.assertEquals(currentNode.getValue(), values.get(index++));
            currentNode = currentNode.next;
        }
        Assert.assertEquals("Unexpected length of your list",values.size()-1, index);
    }



}
