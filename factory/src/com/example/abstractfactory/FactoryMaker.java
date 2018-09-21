package com.example.abstractfactory;

import com.example.abstractfactory.factory.*;

public class FactoryMaker {
	public static KingdomFactory create(KingdomType type) {
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
