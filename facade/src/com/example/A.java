package com.example;

public class A extends Worker {

	@Override
	public void work() {
		System.out.println(name() + " digs for gold.");
	}

	@Override
	public String name() {
		return "A";
	}
}
