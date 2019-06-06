package com.example.v4;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/example/v4/beans.xml");

		User user = (User) context.getBean("userBean");
		user.show();
		
		context.close();
	}
}
