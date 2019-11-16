package com.telerikacademy.tests;

import com.telerikacademy.Queue;
import com.telerikacademy.QueueImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QueueTests {
    private Queue<Integer> testQueue;

    @Before
    public void before() {
        this.testQueue = new QueueImpl<Integer>();
    }


    @Test
    public void offer_should_addElement_whenQueueEmpty() {
        // Act
        testQueue.offer(1);
        // Assert
        Assert.assertEquals(1, (int) testQueue.peek());
    }

    @Test
    public void offer_should_addElement_whenQueueNotEmpty() {
        // Arrange
        testQueue.offer(2);
        // Act
        testQueue.offer(1);
        // Assert
        Assert.assertEquals(2, (int) testQueue.peek());
    }

    @Test(expected = IllegalArgumentException.class)
    public void poll_should_throwException_whenQueueEmpty() {
        // Act&Assert
        testQueue.poll();
    }

    @Test
    public void poll_should_removeProperElement_whenQueueNotEmpty() {
        // Arrange
        testQueue.offer(3);
        testQueue.offer(5);

        // Act
        int elem = testQueue.poll();

        // Assert
        Assert.assertEquals(3, elem);
    }

    @Test
    public void peek_should_returnProperElement_whenOnlyOneElementInQueue() {
        // Arrange
        testQueue.offer(1);

        // Act && Assert
        Assert.assertEquals(1, (int) testQueue.peek());
    }

    @Test
    public void peek_should_returnProperElement_whenQueueHasMultipleElements() {
        // Arrange
        testQueue.offer(4);
        testQueue.offer(5);
        testQueue.offer(6);

        // Act && Assert
        Assert.assertEquals(4, (int) testQueue.peek());
    }

    @Test(expected = IllegalArgumentException.class)
    public void isEmpty_should_throwException_whenQueueEmpty() {
        // Act && Assert
        testQueue.peek();
    }

    @Test
    public void size_should_returnZero_whenQueueEmpty() {
        // Assert
        Assert.assertEquals(0, testQueue.size());
    }

    @Test
    public void size_should_returnProperSize_whenQueueHasMultipeElements() {
        testQueue.offer(1);
        testQueue.offer(4);
        // Assert
        Assert.assertEquals(2, testQueue.size());
    }

    @Test
    public void isEmpty_should_returnTrue_whenQueueIsEmpty() {
        // Assert
        Assert.assertEquals(true, testQueue.isEmpty());
    }


    @Test
    public void isEmpty_should_returnFalse_whenQueueNotEmpty() {
        // Arrange
        testQueue.offer(2);
        // Assert
        Assert.assertEquals(false, testQueue.isEmpty());
    }


}
