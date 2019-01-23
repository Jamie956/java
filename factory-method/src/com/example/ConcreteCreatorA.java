package com.example;

public class ConcreteCreatorA implements Creator{

	@Override
	public Product factoryMethod() {
		// TODO Auto-generated method stub
		return new ConcreteProductA();
	}

}
