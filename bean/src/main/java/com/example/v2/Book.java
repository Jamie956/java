package com.example.v2;

public class Book {
	private Author author;
	private String title;

	public Author getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book [author=" + author + ", title=" + title + "]";
	}
	
}