package com.example;

public class App {
	public static void main(String[] args) {
		Circle circle = ShapeFactory.getShape("CIRCLE");
		circle.draw();
	}
}
