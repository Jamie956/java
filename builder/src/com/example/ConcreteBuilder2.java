package com.example;

public class ConcreteBuilder2 extends Builder{
	private Product product = new Product();
	
	@Override
	public void buildPartA() {
		// TODO Auto-generated method stub
		product.add("PartX");
	}

	@Override
	public void buildPartB() {
		// TODO Auto-generated method stub
		product.add("PartY");
	}

	@Override
	public Product getResult() {
		// TODO Auto-generated method stub
		return product;
	}


}
