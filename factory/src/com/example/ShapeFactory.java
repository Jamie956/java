package com.example;

public class ShapeFactory {
	public static Circle getShape(String shapeType) {
		if (shapeType.equalsIgnoreCase("CIRCLE")) {
			return new Circle();
		} 
		return null;
	}
}
