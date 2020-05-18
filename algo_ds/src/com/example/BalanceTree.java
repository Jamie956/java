package com.example;

public class BalanceTree<T extends Comparable> implements Tree<T> {
    public BalanceTreeNode root;

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public void insert(T data) {
        if (data == null) return;
        this.root = insert(root, data);
    }

    private BalanceTreeNode<T> insert(BalanceTreeNode<T> node, T data) {
        if (node == null) return new BalanceTreeNode<>(data);

        int comp = data.compareTo(node.data);
        if (comp > 0) {
            node.right = insert(node.right, data);
            if (height(node.right) - height(node.left) == 2)
                node = data.compareTo(node.right.data) > 0 ? singleRotateRight(node) : doubleRotateWithRight(node);
        }
        if (comp < 0) {
            node.left = insert(node.left, data);
            if (height(node.left) - height(node.right) == 2)
                node = data.compareTo(node.left.data) < 0 ? singleRotateLeft(node) : doubleRotateWithLeft(node);
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;

    }

    public int height(BalanceTreeNode<T> node) {
        return node == null ? -1 : node.height;
    }

    @Override
    public void remove(T data) {

    }

    @Override
    public BinaryTreeNode findNode(T data) {
        return null;
    }

    @Override
    public T findMax() {
        return null;
    }

    @Override
    public BinaryTreeNode<T> findMax(BinaryTreeNode node) {
        return null;
    }

    @Override
    public T findMin() {
        return null;
    }

    @Override
    public BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
        return null;
    }

    @Override
    public void preOrder() {

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
     * LL
     */
    private BalanceTreeNode<T> singleRotateLeft(BalanceTreeNode<T> x) {
        BalanceTreeNode<T> w = x.left;
        x.left = w.right;
        w.right = x;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        w.height = Math.max(height(w.left), x.height) + 1;
        return x;
    }

    /**
     * RR
     */
    private BalanceTreeNode<T> singleRotateRight(BalanceTreeNode<T> x) {
        BalanceTreeNode<T> w = x.right;
        x.right = w.left;
        w.left = x;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        w.height = Math.max(height(w.left), x.height) + 1;
        return x;
    }

    /**
     * LR
     */
    private BalanceTreeNode<T> doubleRotateWithLeft(BalanceTreeNode<T> x) {
        x.left = singleRotateRight(x.left);
        return singleRotateLeft(x);
    }

    /**
     * RL
     */
    private BalanceTreeNode<T> doubleRotateWithRight(BalanceTreeNode<T> x) {
        x.right = singleRotateLeft(x.right);
        return singleRotateRight(x);
    }
}
