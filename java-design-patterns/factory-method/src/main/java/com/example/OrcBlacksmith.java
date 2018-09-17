package com.example;

public class OrcBlacksmith implements Blacksmith {
	public Weapon manufactureWeapon(WeaponType weaponType) {
		return new OrcWeapon(weaponType);
	}
}
