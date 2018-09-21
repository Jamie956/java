package com.example;

public class App {
	public static void main(String[] args) {
		test03();
	}
	
	public static void test01() {
		AnimalFactory deerFactory = new DeerFactory();
		Animal deer = deerFactory.getAnimal(AnimalType.AWESOME);
		System.out.println(deer.toString());
		System.out.println(deer.getAnimalType());
	}
	
	public static void test02() {
		AnimalFactory deerFactory = new DeerFactory();
		Animal deer = deerFactory.getAnimal(AnimalType.LITTLE);
		System.out.println(deer.toString());
		System.out.println(deer.getAnimalType());
	}
	
	public static void test03() {
		AnimalFactory catFactory = new CatFactory();
		Animal cat = catFactory.getAnimal(AnimalType.AWESOME);
		System.out.println(cat.toString());
		System.out.println(cat.getAnimalType());
	}
	
	public static void test04() {
		AnimalFactory catFactory = new CatFactory();
		Animal cat = catFactory.getAnimal(AnimalType.LITTLE);
		System.out.println(cat.toString());
		System.out.println(cat.getAnimalType());
	}
}
