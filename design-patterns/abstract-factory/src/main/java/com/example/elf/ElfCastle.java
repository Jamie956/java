package com.example.elf;

import com.example.base.Castle;

public class ElfCastle implements Castle {
	static final String DESCRIPTION = "This is the Elven castle!";

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}
}
