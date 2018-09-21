package com.example.factory;

import com.example.base.Army;
import com.example.base.Castle;
import com.example.base.King;
import com.example.orc.OrcArmy;
import com.example.orc.OrcCastle;
import com.example.orc.OrcKing;

public class OrcKingdomFactory implements KingdomFactory {
	public Castle createCastle() {
		return new OrcCastle();
	}

	public King createKing() {
		return new OrcKing();
	}

	public Army createArmy() {
		return new OrcArmy();
	}
}
