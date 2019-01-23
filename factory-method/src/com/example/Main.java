package com.example;

public class Main {
	public static void main(String[] args) {
		Product p1 = new ConcreteCreatorA().factoryMethod();
		System.out.println(p1.getClass().getName());
		
		Product p2 = new ConcreteCreatorB().factoryMethod();
		System.out.println(p2.getClass().getName());
	}
}
