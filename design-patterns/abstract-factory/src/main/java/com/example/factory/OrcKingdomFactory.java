package com.example.factory;

import com.example.entity.Army;
import com.example.entity.Castle;
import com.example.entity.King;
import com.example.entity.OrcArmy;
import com.example.entity.OrcCastle;
import com.example.entity.OrcKing;

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
