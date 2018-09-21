package com.example.factorymethod;

public enum AnimalType {
	AWESOME("awesome"), LITTLE("little");

	private String title;

	AnimalType(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title;
	}
}
