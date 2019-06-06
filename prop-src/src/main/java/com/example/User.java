package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:com/example/user.properties" })
public class User {
	@Value("${user.name}")
	private String name;
	@Value("${user.email}")
	private String email;

	public User() {
		super();
		System.out.println("User Cons");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + "]";
	}

}
