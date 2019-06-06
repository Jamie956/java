package com.example.v1;

import org.springframework.stereotype.Component;

@Component
public class Author {
	private String firstName = "Jamie";
	private String lastName = "Zhou";

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return "Author [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}