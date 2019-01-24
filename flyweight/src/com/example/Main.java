package com.example;

import com.example.PotionFactory.PotionType;

public class Main {
	public static void main(String[] args) {
		PotionFactory factory = new PotionFactory();
		factory.createPotion(PotionType.INVISIBILITY).drink(); // You become invisible. (Potion=6566818)
		factory.createPotion(PotionType.HEALING).drink(); // You feel healed. (Potion=648129364)
		factory.createPotion(PotionType.INVISIBILITY).drink(); // You become invisible. (Potion=6566818)
		factory.createPotion(PotionType.HOLY_WATER).drink(); // You feel blessed. (Potion=1104106489)
		factory.createPotion(PotionType.HOLY_WATER).drink(); // You feel blessed. (Potion=1104106489)
		factory.createPotion(PotionType.HEALING).drink(); // You feel healed. (Potion=648129364)

	}
}
