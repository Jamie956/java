package com.jamie.entity;

public class HelloImpl implements IHello{
	@Override
	public void greeting() {
		System.out.println("Hi");
	}
}
