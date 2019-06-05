package com;

public class Color {
	private String name;
	
	public Color() {
		System.err.println("Color Construct");
	}
	
	public Color(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
