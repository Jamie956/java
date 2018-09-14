package com.example;

public class AdvancedSorceress implements Wizard {
	private Tobacco tobacco;

	public void setTobacco(Tobacco tobacco) {
		this.tobacco = tobacco;
	}

	@Override
	public void smoke() {
		tobacco.smoke(this);
	}
}
