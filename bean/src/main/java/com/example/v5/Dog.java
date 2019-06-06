package com.example.v5;

import org.springframework.stereotype.Component;

@Component(value = "DogBean")
public class Dog {
	public Dog() {
		super();
		System.out.println("dog cons");
	}
	
	public void sayhi() {
		System.out.println("Dog dog hi");
	}
}