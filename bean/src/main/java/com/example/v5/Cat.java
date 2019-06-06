package com.example.v5;

import org.springframework.stereotype.Component;

@Component()
public class Cat {
	public Cat() {
		super();
		System.out.println("Cat");
	}

	public void sayhi() {
		System.out.println("Hi");
	}
}