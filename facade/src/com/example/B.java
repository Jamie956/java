package com.example;

public class B extends Worker {

	@Override
	public void work() {
		System.out.println(name() + " moves gold chunks out of the mine.");
	}

	@Override
	public String name() {
		return "B";
	}
}