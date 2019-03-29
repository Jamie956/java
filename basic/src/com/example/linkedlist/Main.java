package com.example.linkedlist;

import com.example.linkedlist.LinkedList.Node;

public class Main {
	public static void main(String[] args) {
		LinkedList llist = new LinkedList();

		llist.head = new Node(1);
		Node second = new Node(2);
		Node third = new Node(3);
		llist.head.next = second;
		second.next = third;

		llist.printList();
		
		System.out.println();
		
		llist.push(4);
		llist.printList();
		
		System.out.println();
		
		llist.append(5);
		
		llist.printList();
	}
}
