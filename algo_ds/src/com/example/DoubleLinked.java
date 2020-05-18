package com.example;

public class DoubleLinked<E> {
    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;

    private static class Node<E> {
        Node<E> prev;
        Node<E> next;
        E item;

        Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.next = next;
            this.item = item;
        }
    }

    public boolean add(E e) {
        final Node<E> l = last;
        final Node newNode = new Node<E>(last, e, null);

        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            newNode.prev = l;
            l.next = newNode;
        }
        size++;
        return true;
    }

    public void remove(E e) {

    }

    public E get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("index: " + index + " size: " + size);

        if (index < (size >> 1)) {
            Node<E> h = first;
            for (int i = 0; i < index; i++) {
                h = h.next;
            }
            return h.item;
        } else {
            Node<E> t = last;
            for (int i = size - 1; i > index; i--) {
                t = t.prev;
            }
            return t.item;
        }
    }

    public static void main(String[] args) {
        DoubleLinked link = new DoubleLinked();
        link.add(1);
        link.add(2);
        link.add(3);
        link.add(4);

        link.get(0);
        link.get(3);
        link.get(6);
    }


}
