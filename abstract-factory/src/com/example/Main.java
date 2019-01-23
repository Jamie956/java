package com.example;

public class Main {
	public static void main(String[] args) {
		Client client1 = new Client(new ConcreteFactory1());
		client1.run();
		
		Client client2 = new Client(new ConcreteFactory2());
		client2.run();
	}
}
