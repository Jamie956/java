package com.example;

import java.util.ArrayList;

public class Product {
	private ArrayList<String> items = new ArrayList<String>();
	
	public void add(String item) {
		items.add(item);
	}
	
	public void show () {
		for (String item : items) {
			System.out.println(item);
		}
	}
}
