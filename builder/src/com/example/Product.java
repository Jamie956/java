package com.example;

import java.util.ArrayList;

public class Product {
	private ArrayList<String> parts = new ArrayList<String>();
	
	public void add(String part) {
		parts.add(part);
	}
	
	public void show () {
		for (String part : parts) {
			System.out.println(part);
		}
	}
}
