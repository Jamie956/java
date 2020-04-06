package com;

public class HelloImpl implements IHello {

	@Override
	public String sayHello(String string) {
		return "hello:".concat(string);
	}
}