package com.example.v8;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class MyConfig {
	@Bean
	public Book bookBean() {
		return new Book("Java");
	}

	// @Lazy 表示在getBean时才去实例化
	@Bean
	@Lazy
	public Book bookBeanLazy() {
		return new Book("JS");
	}
}
