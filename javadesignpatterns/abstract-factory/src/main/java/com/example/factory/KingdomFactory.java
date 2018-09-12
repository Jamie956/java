package com.example.factory;

import com.example.base.Army;
import com.example.base.Castle;
import com.example.base.King;

public interface KingdomFactory {
	Castle createCastle();

	King createKing();

	Army createArmy();
}
