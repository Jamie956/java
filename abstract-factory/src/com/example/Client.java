package com.example;

public class Client {
	private AbstractProductA abstractProductA;
	private AbstractProductB abstractProductB;
	
	public Client(AbstractFactory factory) {
		abstractProductA = factory.createProductA();
		abstractProductB = factory.createProductB();
	}
	
	public void run () {
		abstractProductB.interact(abstractProductA);
	}
}
