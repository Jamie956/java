package com.example;

public interface Tree<T extends Comparable> {
    boolean isEmpty();

    int size();

    int height();

    void insert(T data);

    void remove(T data);

    BinaryTreeNode findNode(T data);

    T findMax();

    BinaryTreeNode<T> findMax(BinaryTreeNode node);

    T findMin();

    BinaryTreeNode<T> findMin(BinaryTreeNode<T> node);

    void preOrder();

    void inOrder();

    void postOrder();

    void levelOrder();
}
