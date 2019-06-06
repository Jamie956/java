package com.example.v4;

import org.springframework.stereotype.Component;

@Component("userBean")
public class User {
	private String name = "tom";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}