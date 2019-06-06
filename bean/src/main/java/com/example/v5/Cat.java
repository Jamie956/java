package com.example.v5;

import org.springframework.stereotype.Component;

@Component(value = "CatBean")
public class Cat {
	public Cat() {
		super();
		System.out.println("cat cons");
	}

	public void sayhi() {
		System.out.println("Cat cat hi");
	}
}