package com.example;

public class BalanceTreeNode<T extends Comparable> {
    public BalanceTreeNode<T> left;
    public BalanceTreeNode<T> right;
    public T data;
    public int height;

    BalanceTreeNode(T data) {
        this(data, null, null);
    }

    BalanceTreeNode(T data, BalanceTreeNode<T> left, BalanceTreeNode<T> right) {
        this(data, null, null, 0);
    }

    BalanceTreeNode(T data, BalanceTreeNode<T> left, BalanceTreeNode<T> right, int height) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = height;
    }
}
