package com.v2;

public class Main {
	public static void main(String[] args) {
		new Client(new ProductFactory());
		
		new Client(new OrderFactory());
	}
}
