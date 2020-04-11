package com.example;

import java.io.Serializable;

public class Address implements Serializable{
	
	private static final long serialVersionUID = -4537716904357183030L;
	
	public String stress;

	public Address(String stress) {
		super();
		// TODO Auto-generated constructor stub
		this.stress = stress;
	}

	public String getStress() {
		return stress;
	}

	public void setStress(String stress) {
		this.stress = stress;
	}
	
}
