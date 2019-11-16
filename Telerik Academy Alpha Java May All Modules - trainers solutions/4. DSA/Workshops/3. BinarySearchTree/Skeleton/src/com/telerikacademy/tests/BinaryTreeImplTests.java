package com.telerikacademy.tests;

import com.telerikacademy.BinarySearchTreeImpl;
import com.telerikacademy.BinaryTreeNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BinaryTreeImplTests {
    private BinarySearchTreeImpl testTree;

    @Before
    public void before() {
        testTree = new BinarySearchTreeImpl();
    }

    @Test
    public void insert_should_updateRoot_whenTreeEmpty() {
        // Act
        testTree.insert(5);

        // Assert
        Assert.assertEquals(5, testTree.getRoot().getValue());
    }

    @Test
    public void insert_should_addLeftNodeCorrectly_whenTreeHasRootOnly() {
        testTree.insert(5);
        // Act
        testTree.insert(4);

        // Assert
        Assert.assertEquals(4, testTree.getRoot().getLeftChild().getValue());
    }

    @Test
    public void insert_should_addRightNodeCorrectly_whenTreeHasRootOnly() {
        //Arrange
        testTree.insert(5);
        // Act
        testTree.insert(7);

        // Assert
        Assert.assertEquals(7, testTree.getRoot().getRightChild().getValue());
    }

    @Test
    public void insert_should_addLeftNodeCorrectly_whenTreeHasMultipleNodes() {
        // Arrange
        prepareTestTree();
        // Act
        testTree.insert(25);

        // Assert
        Assert.assertEquals(25, testTree.getRoot().getLeftChild().getLeftChild().getRightChild().getValue());
    }

    @Test
    public void insert_should_addRightNodeCorrectly_whenTreeHasMultipleNodes() {
        // Arrange
        prepareTestTree();
        // Act
        testTree.insert(65);

        // Assert
        Assert.assertEquals(65, testTree.getRoot().getRightChild().getLeftChild().getRightChild().getValue());
    }

    @Test
    public void search_should_returnNull_whenTreeEmpty() {
        // Act
        BinaryTreeNode node = testTree.search(5);
        // Assert
        Assert.assertNull(node);
    }

    @Test
    public void search_should_returnNull_whenNodeDoesNotExist() {
        // Arrange
        prepareTestTree();

        // Act
        BinaryTreeNode node = testTree.search(5);
        // Assert
        Assert.assertNull(node);
    }

    @Test
    public void search_should_returnTheNode_whenTreeHasRootOnly() {
        // Arrange
        testTree.insert(10);

        // Act
        BinaryTreeNode node = testTree.search(10);

        // Assert
        Assert.assertEquals(10, node.getValue());
    }

    @Test
    public void search_should_returnTheNode_whenTreeHasMultipleNodes() {
        prepareTestTree();

        // Act
        BinaryTreeNode node = testTree.search(60);

        // Assert
        Assert.assertEquals(60, node.getValue());
    }


    @Test
    public void preOrder_should_returnElementsInRightSequence() {
        // Arrange
        prepareTestTree();
        Integer[] expected = {50, 30, 20, 40, 70, 60, 80, 72, 71};
        // Act
        List<Integer> result = testTree.preOrder();

        // Assert
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void preOrder_should_returnEmptyList_whenTreeIsEmpty() {
        // Arrange
        Integer[] expected = {};
        // Act
        List<Integer> result = testTree.preOrder();

        // Assert
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void inOrder_should_returnElementsInRightSequence() {
        // Arrange
        prepareTestTree();
        Integer[] expected = {20, 30, 40, 50, 60, 70, 71, 72, 80};
        // Act
        List<Integer> result = testTree.inOrder();

        // Assert
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void inOrder_should_returnEmptyList_whenTreeIsEmpty() {
        // Arrange
        Integer[] expected = {};
        // Act
        List<Integer> result = testTree.inOrder();

        // Assert
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void postOrder_should_returnElementsInRightSequence() {
        // Arrange
        prepareTestTree();
        Integer[] expected = {20, 40, 30, 60, 71, 72, 80, 70, 50};
        // Act
        List<Integer> result = testTree.postOrder();

        // Assert
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void postOrder_should_returnEmptyList_whenTreeIsEmpty() {
        // Arrange
        Integer[] expected = {};
        // Act
        List<Integer> result = testTree.postOrder();

        // Assert
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void bfs_should_returnElementsInRightSequence() {
        // Arrange
        prepareTestTree();
        Integer[] expected = {50, 30, 70, 20, 40, 60, 80, 72, 71};
        // Act
        List<Integer> result = testTree.bfs();

        // Assert
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void bfs_should_returnEmptyList_whenTreeIsEmpty() {
        // Arrange
        Integer[] expected = {};
        // Act
        List<Integer> result = testTree.bfs();

        // Assert
        Assert.assertArrayEquals(expected, result.toArray());
    }

    @Test
    public void height_should_returnMinusOne_whenTreeIsEmpty() {
        // Act & Assert
        Assert.assertEquals(-1, testTree.height());
    }

    @Test
    public void height_should_returnZero_whenTreeHasRootOnly() {
        // Arrange
        testTree.insert(5);

        // Act & Assert
        Assert.assertEquals(0, testTree.height());
    }

    @Test
    public void height_should_returnCorrectHeight() {

        // Arrange
        prepareTestTree();

        // Act & Assert
        Assert.assertEquals(4, testTree.height());
    }

    // Uncomment when implementing remove method
//    @Test
//    public void remove_should_returnNull_whenTreeIsEmpty() {
//        // Act
//        BinaryTreeNode removed = testTree.remove(5);
//
//        // Assert
//        Assert.assertNull(removed);
//    }
//
//    @Test
//    public void remove_should_replaceRoot_whenValueToBeRemovedIsInRoot() {
//        // Arrange
//        prepareTestTree();
//        // Act
//        BinaryTreeNode removed = testTree.remove(50);
//        // Assert
//        Assert.assertEquals(60, removed.getValue());
//    }
//
//    @Test
//    public void remove_should_maintainCorrectOrdering_whenValuePresentAndHasRightChildOnly() {
//        // Arrange
//        prepareTestTree();
//        testTree.insert(42);
//        // Act
//        BinaryTreeNode removed = testTree.remove(40);
//        // Assert
//        Assert.assertEquals(42, testTree.getRoot().getLeftChild().getRightChild().getValue());
//    }
//
//    @Test
//    public void remove_should_maintainCorrectOrdering_whenValuePresentAndHasLeftChildOnly() {
//        // Arrange
//        prepareTestTree();
//        testTree.insert(39);
//        // Act
//        BinaryTreeNode removed = testTree.remove(40);
//        // Assert
//        Assert.assertEquals(39, testTree.getRoot().getLeftChild().getRightChild().getValue());
//    }
//
//    @Test
//    public void remove_should_maintainCorrectOrdering_whenValuePresentAndHasBothChildren() {
//        // Arrange
//        prepareTestTree();
//        // Act
//        BinaryTreeNode removed = testTree.remove(70);
//        // Assert
//        Assert.assertEquals(71, testTree.getRoot().getRightChild().getValue());
//        Assert.assertNull(testTree.getRoot().getRightChild().getRightChild().getLeftChild().getLeftChild());
//    }


    private void prepareTestTree() {
        testTree.insert(50);
        testTree.insert(30);
        testTree.insert(20);
        testTree.insert(40);
        testTree.insert(70);
        testTree.insert(60);
        testTree.insert(80);
        testTree.insert(72);
        testTree.insert(71);
    }

}
