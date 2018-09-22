package com.example;

public final class Hero {
	private final Profession profession;
	private final String name;
	private final HairType hairType;
	private final HairColor hairColor;
	private final Armor armor;
	private final Weapon weapon;

	Hero(HeroBuilder builder) {
		this.profession = builder.profession;
		this.name = builder.name;
		this.hairColor = builder.hairColor;
		this.hairType = builder.hairType;
		this.weapon = builder.weapon;
		this.armor = builder.armor;
	}

	public Profession getProfession() {
		return profession;
	}

	public String getName() {
		return name;
	}

	public HairType getHairType() {
		return hairType;
	}

	public HairColor getHairColor() {
		return hairColor;
	}

	public Armor getArmor() {
		return armor;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	@Override
	public String toString() {
		return "Hero [profession=" + profession + ", name=" + name + ", hairType=" + hairType + ", hairColor="
				+ hairColor + ", armor=" + armor + ", weapon=" + weapon + "]";
	}
}
