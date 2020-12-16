package com.jamie.bean.annotation;

import org.springframework.stereotype.Component;

@Component
public class Author {
	public Author () {
		System.out.println("New Author");
	}
}