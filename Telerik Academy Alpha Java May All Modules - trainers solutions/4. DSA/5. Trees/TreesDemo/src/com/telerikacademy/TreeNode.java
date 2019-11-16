package com.telerikacademy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {

    int value;
    List<TreeNode> children;

    public TreeNode(int value) {
        this.value = value;
        children = new ArrayList<>();
    }

    public void addChild(TreeNode node) {
        children.add(node);
    }

    public void addChild(Integer... values) {
        for (int val : values) {
            children.add(new TreeNode(val));
        }
    }

    public void bfs() {

        Queue<TreeNode> toBeVisited = new LinkedList<>();
        toBeVisited.offer(this);

        while (!toBeVisited.isEmpty()) {
            TreeNode current = toBeVisited.poll();

            //use it
            System.out.printf("%d, ", current.value);

            toBeVisited.addAll(current.children);
        }

    }

    public void dfs() {

        System.out.printf("%d, ", this.value);
        if (this.children != null && !children.isEmpty()) {
            for (TreeNode node : children) {
                node.dfs();
            }
        }
    }

}
