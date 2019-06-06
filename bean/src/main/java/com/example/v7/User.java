package com.example.v7;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:com/example/v7/user.properties" })
public class User {
	@Value("${user.a}")
	private String a;
	
	@Override
	public String toString() {
		return "User [a=" + a + "]";
	}
}
