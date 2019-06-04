package com.example;

public class Builder{
	private Product product = new Product();
	
	public void buildA() {
		product.add("Item A");
	}

	public void buildB() {
		product.add("Item B");
	}

	public Product get() {
		return product;
	}

}
