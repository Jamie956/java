package com.example;

public class BinaryTreeNode<T extends Comparable> {
    public BinaryTreeNode<T> left;
    public BinaryTreeNode<T> right;
    public T data;

    BinaryTreeNode(T data) {
        this(data, null, null);
    }

    BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
