package com.example.v3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/example/v3/beans.xml");
		User user = (User) context.getBean("userBean");
		String name = user.getName();
		System.out.println(name);
		
		context.close();
	}
}
