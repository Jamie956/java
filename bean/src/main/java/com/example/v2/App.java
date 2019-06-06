package com.example.v2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		String foo = (String) context.getBean("foo");
		System.out.println(foo);
		
		
		Author author = (Author) context.getBean("authorBean");
		System.out.println(author);
		
		Book book = (Book) context.getBean("bookBean");
		System.out.println(book);
		
		context.close();
	}
}