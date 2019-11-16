package com.telerikacademytests;

import com.telerikacademy.MyArrayList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyArrayListTests {
    private MyArrayList<Integer> list;

    @Before
    public void initializeTest() {
        System.out.println("initialize test");

        list = new MyArrayList<>();
    }

    @After
    public void cleanupTest() {
        System.out.println("clean after test");

        list = null;
    }

    @Test
    public void add_Should_IncreaseSize() {
        System.out.println("test 1");

        // Act
        list.add(1);

        // Assert
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void constructor_Should_CreateEmptyList() {
        System.out.println("test 2");

        // Assert
        Assert.assertEquals("New list should be empty", 0, list.size());
    }

    @Test
    public void get_Should_ReturnCorrectItemByIndex() {
        System.out.println("test 3");

        list.add(1);

        int element = list.get(0);
        Assert.assertEquals(1, element);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_Should_ThrowException_When_ElementNotExist() {
        System.out.println("test 4");

        // Act
        int element = list.get(0);
    }
}
