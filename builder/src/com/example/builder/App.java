package com.example.builder;

public class App {
	public static void main(String[] args) {
		Builder builder = new Builder();
		new Director(builder);
		Product product = builder.get();
		product.show();
	}
	
}
