package com.example;

/**
 * 1. 接口
 * 2. 接口实现类
 * 3. jdk代理，实现InvocationHandler接口
 * @author jamie
 *
 */
public class App {
	public static void main(String[] args) {
		DynamicProxy dp = new DynamicProxy(new HelloImpl());
		IHello helloProxy = dp.getProxy();
		
		helloProxy.greeting();
	}
}
