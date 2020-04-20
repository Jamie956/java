package com;

public class HelloImpl implements Hello {

	@Override
	public String sayHello(String string) {
		return "hello:".concat(string);
	}
}