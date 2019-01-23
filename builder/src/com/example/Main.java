package com.example;

public class Main {
	public static void main(String[] args) {
		Builder builder1 = new ConcreteBuilder1();
		new Director(builder1);
		Product product1 = builder1.getResult();
		product1.show();
		
		Builder builder2 = new ConcreteBuilder2();
		new Director(builder2);
		Product product2 = builder2.getResult();
		product2.show();
	}
}
