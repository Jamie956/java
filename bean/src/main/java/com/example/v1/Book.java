package com.example.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Book {
	@Autowired
	private Author author;
	private String title = "Thinking in Java";

	public Author getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return "Book [author=" + author + ", title=" + title + "]";
	}
	
}