package com.example.v6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class MyConfig {
	@Bean
	public Book bookBean() {
		return new Book("A");
	}

	// getBean时才实例化
	@Bean
	@Lazy
	public Book bookBeanLazy() {
		return new Book("B");
	}
}
