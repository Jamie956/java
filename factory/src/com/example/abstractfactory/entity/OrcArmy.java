package com.example.abstractfactory.entity;

public class OrcArmy implements Army {
	static final String DESCRIPTION = "This is the Orc Army!";

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}
}
