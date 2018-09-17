package com.example;

public class App {
	public static void main(String[] args) {
		test02();
	}

	public static void test01() {
		Blacksmith blacksmith = new OrcBlacksmith();
		
		Weapon weapon1 = blacksmith.manufactureWeapon(WeaponType.SPEAR);
		System.out.println(weapon1.toString());
		
		Weapon weapon2 = blacksmith.manufactureWeapon(WeaponType.AXE);
		System.out.println(weapon2.toString());
	}

	public static void test02() {
		Blacksmith blacksmith = new ElfBlacksmith();
		
		Weapon weapon1 = blacksmith.manufactureWeapon(WeaponType.SPEAR);
		System.out.println(weapon1.toString());
		
		Weapon weapon2 = blacksmith.manufactureWeapon(WeaponType.AXE);
		System.out.println(weapon2.toString());
	}
}
