package com.example;

public class App {
	public static void main(String[] args) {
		test01();
	}

	public static void test01() {
		Hero mage = new Hero.Builder(Profession.MAGE, "Riobard").withHairColor(HairColor.BLACK)
				.withWeapon(Weapon.DAGGER).build();
		System.out.println(mage.toString());
	}

	public static void test02() {
		Hero warrior = new Hero.Builder(Profession.WARRIOR, "Amberjill").withHairColor(HairColor.BLOND)
				.withHairType(HairType.LONG_CURLY).withArmor(Armor.CHAIN_MAIL).withWeapon(Weapon.SWORD).build();
		System.out.println(warrior.toString());
	}

	public static void test03() {
		Hero thief = new Hero.Builder(Profession.THIEF, "Desmond").withHairType(HairType.BALD).withWeapon(Weapon.BOW)
				.build();
		System.out.println(thief.toString());
	}
}
