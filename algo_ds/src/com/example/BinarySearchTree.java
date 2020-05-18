package com.example;

import com.sun.source.tree.BinaryTree;

public class BinarySearchTree<T extends Comparable> implements Tree<T> {

    public BinaryTreeNode<T> root;

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(BinaryTreeNode<T> node) {
        if (node == null) return 0;
        int l = size(node.left);
        int r = size(node.right);
        return l + r + 1;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(BinaryTreeNode<T> node) {
        if (node == null) return 0;
        int l = height(node.left);
        int r = height(node.right);

        return l > r ? l + 1 : r + 1;
    }

    @Override
    public void insert(T data) {
        if (data == null) return;
        insert(root, data);
    }

    private BinaryTreeNode<T> insert(BinaryTreeNode<T> node, T data) {
        if (node == null) return new BinaryTreeNode<T>(data);

        int comp = data.compareTo(node.data);
        if (comp > 0) node.right = insert(node.right, data);
        if (comp < 0) node.left = insert(node.left, data);

        return node;

    }

    @Override
    public void remove(T data) {
        if (data == null) return;
        root = remove(root, data);
    }

    private BinaryTreeNode<T> remove(BinaryTreeNode<T> node, T data) {
        if (node == null) return null;
        int comp = data.compareTo(node.data);
        if (comp > 0) {
            node.right = remove(node.right, data);
        }
        if (comp < 0) {
            node.left = remove(node.left, data);
        }
        if (comp == 0) {
            if (node.left != null && node.right != null) {
                node.data = findMin(node.right).data;
                node.right = remove(node.right, node.data);
            } else {
                node = node.left != null ? node.left : node.right;
            }

        }
        return node;
    }

    @Override
    public BinaryTreeNode findNode(T data) {
        return null;
    }

    @Override
    public T findMax() {
        return findMax(root).data;
    }

    @Override
    public BinaryTreeNode<T> findMax(BinaryTreeNode node) {
        if (node == null) return null;
        if (node.right == null) return node;

        return findMax(node.right);
    }

    @Override
    public T findMin() {
        return findMin(root).data;
    }

    @Override
    public BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
        if (node == null) return null;
        if (node.left == null) return node;

        return findMin(node.left);
    }

    @Override
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(BinaryTreeNode<T> node) {
        if (node==null) return;
        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    @Override
    public void inOrder() {

    }

    @Override
    public void postOrder() {

    }

    @Override
    public void levelOrder() {

    }

    /**
     * 6
     * 2    7
     * 1 5
     */
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree<>();

        BinaryTreeNode root = new BinaryTreeNode<Integer>(6, new BinaryTreeNode<Integer>(2), new BinaryTreeNode<Integer>(7));

        tree.root = root;

        tree.insert(1);
        tree.insert(5);
//        tree.insert(3);
//        tree.insert(4);
        tree.preOrder();

//        tree.remove(2);
    }
}
