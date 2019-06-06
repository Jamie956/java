package com.example.v6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
	@Bean
	@Conditional(IsActive.class)
	public Item itemBean() {
		return new Item();
	}
}