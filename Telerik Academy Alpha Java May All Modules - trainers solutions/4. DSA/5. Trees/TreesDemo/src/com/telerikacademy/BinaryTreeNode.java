package com.telerikacademy;

public class BinaryTreeNode {

    int value;
    BinaryTreeNode left, right;

    public BinaryTreeNode(int value) {
        this.value = value;
    }

    public void dfs() {

        if (this.left != null) {
            this.left.dfs();
        }
        if (this.right != null) {
            this.right.dfs();
        }

        System.out.printf("%d, ", this.value);
    }


}
