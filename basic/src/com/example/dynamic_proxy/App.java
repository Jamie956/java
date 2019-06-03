package com.example.dynamic_proxy;


public class App {
	public static void main(String[] args) {
		MyInvo myInvo = new MyInvo(new DeerImpl());
		IDeer proxyObj = (IDeer)myInvo.getProxyObject();
		proxyObj.running();
	}
}
