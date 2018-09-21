package com.example.factory;

import com.example.entity.Army;
import com.example.entity.Castle;
import com.example.entity.King;

public interface KingdomFactory {
	Castle createCastle();

	King createKing();

	Army createArmy();
}
