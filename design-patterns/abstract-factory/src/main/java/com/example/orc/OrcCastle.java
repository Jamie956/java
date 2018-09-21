package com.example.orc;

import com.example.base.Castle;

public class OrcCastle implements Castle {
	static final String DESCRIPTION = "This is the Orc castle!";

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}
}
