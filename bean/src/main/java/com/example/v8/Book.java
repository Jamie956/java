package com.example.v8;

public class Book {
	private String name;

	public Book(String name) {
		this.name = name;
		System.out.println("Book cons, "+name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}