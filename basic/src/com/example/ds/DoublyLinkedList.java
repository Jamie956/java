package com.example.ds;

public class DoublyLinkedList {
	Node head;

	static class Node {
		int data;
		Node prev;
		Node next;

		Node(int d) {
			data = d;
		}
	}

	public void push(int new_data) {
		Node new_Node = new Node(new_data);

		new_Node.next = head;
		new_Node.prev = null;

		if (head != null)
			head.prev = new_Node;

		head = new_Node;
	}

	public void InsertAfter(Node prev_Node, int new_data) {

		if (prev_Node == null) {
			System.out.println("The given previous node cannot be NULL ");
			return;
		}

		Node new_node = new Node(new_data);

		new_node.next = prev_Node.next;
		prev_Node.next = new_node;
		new_node.prev = prev_Node;

		if (new_node.next != null)
			new_node.next.prev = new_node;
	}

	void append(int new_data) {
		Node new_node = new Node(new_data);

		Node last = head;
		new_node.next = null;

		if (head == null) {
			new_node.prev = null;
			head = new_node;
			return;
		}

		while (last.next != null)
			last = last.next;

		last.next = new_node;
		new_node.prev = last;
	}

}
