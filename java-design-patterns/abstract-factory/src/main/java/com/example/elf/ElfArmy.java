package com.example.elf;

import com.example.base.Army;

public class ElfArmy implements Army {
	static final String DESCRIPTION = "This is the Elven Army!";

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}
}
