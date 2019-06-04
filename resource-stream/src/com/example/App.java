package com.example;

public class App {
	public static void main(String[] args) {
		Deer deer = (Deer) ObjectFactory.getInstance("Deer.class");
		deer.sayhi();
	}
}