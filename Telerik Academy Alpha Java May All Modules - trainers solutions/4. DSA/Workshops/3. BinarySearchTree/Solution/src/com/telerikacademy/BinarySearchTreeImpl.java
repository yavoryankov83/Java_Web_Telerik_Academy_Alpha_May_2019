package com.telerikacademy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTreeImpl implements BinarySearchTree {

    private BinaryTreeNode root;

    @Override
    public BinaryTreeNode getRoot() {
        return root;
    }

    @Override
    public void insert(int value) {
        root = insertRecursively(root, value);
    }

    @Override
    public BinaryTreeNode search(int value) {
        return searchRecursively(root, value);
    }

    @Override
    public List<Integer> inOrder() {
        List<Integer> result = new ArrayList<>();
        inOrderRecursively(root, result);
        return result;
    }

    @Override
    public List<Integer> postOrder() {
        List<Integer> result = new ArrayList<>();
        postOrderRecursively(root, result);
        return result;
    }

    @Override
    public List<Integer> preOrder() {
        List<Integer> result = new ArrayList<>();
        preOrderRecursively(root, result);
        return result;
    }

    @Override
    public List<Integer> bfs() {
        List<Integer> result = new ArrayList<>();
        breadthFirstSearchRecursively(root, result);
        return result;
    }

    @Override
    public int height() {
        return findTreeHeightRecursively(root);
    }

    @Override
    public BinaryTreeNode remove(int value) {
        root = removeNodeRecursively(root, value);
        return root;

    }

    /**
     * Finds the node that will replace the removed node (the biggest one in the left sub-tree of the node being removed).
     *
     * @param nodeToBeRemoved - the node that will be removed/replaced.
     * @return the value of the node that will take the removed nodes' place.
     */
    private int minNodeForRemove(BinaryTreeNode nodeToBeRemoved) {
        int minValue = nodeToBeRemoved.getValue();
        while (nodeToBeRemoved.getLeftChild() != null) {
            minValue = nodeToBeRemoved.getLeftChild().getValue();
            nodeToBeRemoved = nodeToBeRemoved.getLeftChild();
        }
        return minValue;
    }

    private BinaryTreeNode removeNodeRecursively(BinaryTreeNode root, int value) {
        if (root == null)
            return null;
        if (root.getValue() > value) {
            root.setLeftChild(removeNodeRecursively(root.getLeftChild(), value));
        } else if (root.getValue() < value) {
            root.setRightChild(removeNodeRecursively(root.getRightChild(), value));

        } else {
            if (root.getLeftChild() == null)
                return root.getRightChild();
            else if (root.getRightChild() == null)
                return root.getLeftChild();

            root.setValue(minNodeForRemove(root.getRightChild()));

            root.setRightChild(removeNodeRecursively(root.getRightChild(), root.getValue()));
            return root;

        }
        return root;

    }

    private void breadthFirstSearchRecursively(BinaryTreeNode root, List<Integer> result) {
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        if (root == null) {
            return;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.remove();
            result.add(node.getValue());
            if (node.getLeftChild() != null) queue.add(node.getLeftChild());
            if (node.getRightChild() != null) queue.add(node.getRightChild());
        }

    }

    private int findTreeHeightRecursively(BinaryTreeNode root) {
        if (root == null) {
            return -1;
        }

        int leftHeight = findTreeHeightRecursively(root.getLeftChild());
        int rightHeight = findTreeHeightRecursively(root.getRightChild());

        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
    }

    private void postOrderRecursively(BinaryTreeNode root, List<Integer> result) {
        if (root != null) {
            postOrderRecursively(root.getLeftChild(), result);
            postOrderRecursively(root.getRightChild(), result);
            result.add(root.getValue());
        }
    }

    private void preOrderRecursively(BinaryTreeNode root, List<Integer> result) {
        if (root != null) {
            result.add(root.getValue());
            preOrderRecursively(root.getLeftChild(), result);
            preOrderRecursively(root.getRightChild(), result);
        }
    }

    private void inOrderRecursively(BinaryTreeNode root, List<Integer> integers) {
        if (root != null) {
            inOrderRecursively(root.getLeftChild(), integers);
            integers.add(root.getValue());
            inOrderRecursively(root.getRightChild(), integers);
        }

    }

    private BinaryTreeNode searchRecursively(BinaryTreeNode root, int value) {
        if (root == null || root.getValue() == value)
            return root;

        if (root.getValue() > value)
            return searchRecursively(root.getLeftChild(), value);

        return searchRecursively(root.getRightChild(), value);
    }

    private BinaryTreeNode insertRecursively(BinaryTreeNode root, int value) {

        if (root == null) {
            root = new BinaryTreeNode(value);
            return root;
        }

        if (value < root.getValue())
            root.setLeftChild(insertRecursively(root.getLeftChild(), value));
        else if (value > root.getValue())
            root.setRightChild(insertRecursively(root.getRightChild(), value));

        return root;
    }


}
