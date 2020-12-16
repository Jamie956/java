package com.jamie.bean.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Book {
	@Autowired
	private Author author;
	
	public Book() {
		System.out.println("New Book");
	}
	
	@Override
	public String toString() {
		return "Book [author=" + author + "]";
	}
}