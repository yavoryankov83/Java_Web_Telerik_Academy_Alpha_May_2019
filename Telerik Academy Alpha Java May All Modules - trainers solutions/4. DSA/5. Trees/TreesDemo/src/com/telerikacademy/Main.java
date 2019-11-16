package com.telerikacademy;

public class Main {

    public static void main(String[] args) {
        tree();
        binaryTree();
    }

    private static void tree() {

        TreeNode root = new TreeNode(11);
        TreeNode node1 = new TreeNode(8);
        root.addChild(node1);
        root.addChild(1);
        node1.addChild(6, 4);
        TreeNode node2 = new TreeNode(17);
        node1.addChild(node2);
        node2.addChild(99, 77);

        System.out.print("BFS: ");
        root.bfs();
        System.out.println();

        System.out.print("DFS: ");
        root.dfs();

    }

    private static void binaryTree() {
        BinaryTreeNode root = new BinaryTreeNode(8);
        root.left = new BinaryTreeNode(66);
        BinaryTreeNode node1 = new BinaryTreeNode(5);
        root.right = node1;
        node1.left = new BinaryTreeNode(3);
        node1.right = new BinaryTreeNode(9);

        System.out.println("");
        System.out.print("DFS: ");
        root.dfs();
    }
}
