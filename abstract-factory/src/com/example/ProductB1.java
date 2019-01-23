package com.example;

public class ProductB1 extends AbstractProductB {

	@Override
	public void interact(AbstractProductA a) {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getSimpleName() + " interacts with " + a.getClass().getSimpleName());
	}

}
