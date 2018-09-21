package com.example.factorymethod;

public class Cat implements Animal {
	Cat(){
		System.out.println("Cat");
	}
	
	@Override
	public void eat() {
		System.out.println("Cat eat");
	}
}
