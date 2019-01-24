package com.example.v2;

public class Main {
	public static void main(String[] args) {
		RefinedAbstraction ra1 = new RefinedAbstraction(new ConcreteImplementorA());
		ra1.Operation();
		ra1.getImplementor().operation();
	}
}
