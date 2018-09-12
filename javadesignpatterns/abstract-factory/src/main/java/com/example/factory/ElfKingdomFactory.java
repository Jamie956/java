package com.example.factory;

import com.example.base.Army;
import com.example.base.Castle;
import com.example.base.King;
import com.example.elf.ElfArmy;
import com.example.elf.ElfCastle;
import com.example.elf.ElfKing;

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
