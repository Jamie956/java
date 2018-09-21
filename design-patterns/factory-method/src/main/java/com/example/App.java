package com.example;

public class App {
	public static void main(String[] args) {
		test01();
	}

	public static void test01() {
		AnimalFactory deerFactory = new DeerFactory();
		Animal deer = deerFactory.getAnimal(AnimalType.AWESOME);
		System.out.println(deer.toString());
		
		AnimalFactory deerFactory2 = new DeerFactory();
		Animal deer2 = deerFactory2.getAnimal(AnimalType.LITTLE);
		System.out.println(deer2.toString());
	}

}
