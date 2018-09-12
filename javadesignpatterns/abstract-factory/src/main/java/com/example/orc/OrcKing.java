package com.example.orc;

import com.example.base.King;

public class OrcKing implements King {
	static final String DESCRIPTION = "This is the Orc king!";

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}
}
