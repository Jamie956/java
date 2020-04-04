package com.example;

public class HelloProxy implements IHello{
	private IHello target = null;
	HelloProxy(){
		target = new HelloImpl();
	}
	public void greeting() {
		before();
		target.greeting();
		after();
	}
	
	private void before() {
		System.out.println("before");
	}
	private void after() {
		System.out.println("after");
	}
}
