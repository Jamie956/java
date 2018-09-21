package com.example.factorymethod;

public class Dog implements Animal {
	Dog(){
		System.out.println("Dog");
	}
	
	@Override
	public void eat() {
		System.out.println("Dog eat");
	}
}
