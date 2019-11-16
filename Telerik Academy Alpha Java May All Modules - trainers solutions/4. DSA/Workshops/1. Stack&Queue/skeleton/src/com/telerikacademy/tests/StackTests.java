package com.telerikacademy.tests;


import com.telerikacademy.Stack;
import com.telerikacademy.StackImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StackTests {
    private StackImpl<Integer> testStack;
    @Before
    public void before() {
        this.testStack = new StackImpl<>();
    }

    @Test
    public void push_should_pushElement_when_stackEmpty() {
        // Act
        testStack.push(1);
        // Assert
        Assert.assertEquals(1, (int)testStack.peek());
    }

    @Test
    public void push_should_pushElement_when_StackNotEmpty() {
        // Arrange
        testStack.push(1);

        // Act
        testStack.push(3);

        Assert.assertEquals(3, (int)testStack.peek());
    }

    @Test(expected = IllegalArgumentException.class)
    public void pop_should_throwException_whenStackEmpty() {
        // Act
        testStack.pop();
    }

    @Test
    public void pop_should_returnCorrectValue_whenStackNotEmpty() {
        // Arrange
        testStack.push(5);
        testStack.push(11);
        testStack.push(3);

        // Act&&Assert
        Assert.assertEquals(3, (int)testStack.pop());
    }

    @Test(expected = IllegalArgumentException.class)
    public void peek_should_throwException_whenStackEmpty() {
        // Act
        testStack.peek();
    }

    @Test
    public void peek_should_returnCorrectValue_whenStackNotEmpty() {
        // Arrange
        testStack.push(5);
        testStack.push(11);
        testStack.push(3);

        // Act&&Assert
        Assert.assertEquals(3, (int)testStack.peek());
    }

    @Test
    public void size_should_returnZero_whenStackEmpty() {
        // Act&&Assert

        Assert.assertEquals(0,testStack.size());
    }

    @Test
    public void size_should_returnProperValue_whenStackNotEmpty() {
        // Arrange
        testStack.push(1);
        testStack.push(11);
        testStack.push(3);
        // Act&&Assert

        Assert.assertEquals(3,testStack.size());
    }

    @Test
    public void isEmpty_should_returnTrue_whenStackEmpty() {
        // Assert
        Assert.assertEquals(true, testStack.isEmpty());
    }

    @Test
    public void isEmpty_should_returnFalse_whenStackNotEmpty() {
        // Act
        testStack.push(5);

        // Assert
        Assert.assertEquals(false, testStack.isEmpty());
    }


}
