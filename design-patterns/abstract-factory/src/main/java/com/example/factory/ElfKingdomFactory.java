package com.example.factory;

import com.example.entity.Army;
import com.example.entity.Castle;
import com.example.entity.ElfArmy;
import com.example.entity.ElfCastle;
import com.example.entity.ElfKing;
import com.example.entity.King;

public class ElfKingdomFactory implements KingdomFactory {
	public Castle createCastle() {
		return new ElfCastle();
	}

	public King createKing() {
		return new ElfKing();
	}

	public Army createArmy() {
		return new ElfArmy();
	}
}
