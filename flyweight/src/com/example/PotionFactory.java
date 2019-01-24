package com.example;

import java.util.*;

public class PotionFactory {

	private final Map<PotionType, Potion> potions;

	static enum PotionType {
		HEALING, HOLY_WATER, INVISIBILITY
	}

	public PotionFactory() {
		potions = new EnumMap<>(PotionType.class);
	}

	Potion createPotion(PotionType type) {
		Potion potion = potions.get(type);
		if (potion == null) {
			switch (type) {
			case HEALING:
				potion = new HealingPotion();
				potions.put(type, potion);
				break;
			case HOLY_WATER:
				potion = new HolyWaterPotion();
				potions.put(type, potion);
				break;
			case INVISIBILITY:
				potion = new InvisibilityPotion();
				potions.put(type, potion);
				break;
			default:
				break;
			}
		}
		return potion;
	}
}
