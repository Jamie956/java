package com.example;

public class App {
	public static void main(String[] args) {
		IHello helloProxy = CGLibProxy.getInstance().getProxy(HelloImpl.class);
		helloProxy.greeting();
	}
}
