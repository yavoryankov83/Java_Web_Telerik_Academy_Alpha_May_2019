package com.telerikacademy;

public class BinaryTreeNode {

    private int value;
    private BinaryTreeNode leftChild;
    private BinaryTreeNode rightChild;

    public void setLeftChild(BinaryTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(BinaryTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public BinaryTreeNode(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public BinaryTreeNode getLeftChild() {
        return leftChild;
    }

    public BinaryTreeNode getRightChild() {
        return rightChild;
    }

}
