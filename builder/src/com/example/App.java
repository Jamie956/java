package com.example;

public class App {
	public static void main(String[] args) {
		test01();
	}

	public static void test01() {
		Hero hero = new HeroBuilder(Profession.MAGE, "Riobard")
				.withHairColor(HairColor.BLACK)
				.withWeapon(Weapon.DAGGER)
				.build();
		
		System.out.println(hero);
	}
}
