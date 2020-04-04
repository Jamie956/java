package com.example;

/**
 * 1. 接口 IHello
 * 2. 接口实现类 HelloImpl
 * 3. 代理类，构造时new一个接口实现类 HelloProxy
 * @author jamie
 *
 */
public class App {
	public static void main(String[] args) {
		IHello helloProxy = new HelloProxy();
		helloProxy.greeting();
	}
}
