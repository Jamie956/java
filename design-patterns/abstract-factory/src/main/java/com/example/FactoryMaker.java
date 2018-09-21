package com.example;

import com.example.factory.ElfKingdomFactory;
import com.example.factory.KingdomFactory;
import com.example.factory.OrcKingdomFactory;

public class FactoryMaker {
	public enum KingdomType {
		ELF, ORC
	}

	public static KingdomFactory makeFactory(KingdomType type) {
		switch (type) {
		case ELF:
			return new ElfKingdomFactory();
		case ORC:
			return new OrcKingdomFactory();
		default:
			throw new IllegalArgumentException("KingdomType not supported.");
		}
	}
}
