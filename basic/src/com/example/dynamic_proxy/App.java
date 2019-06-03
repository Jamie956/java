package com.example.dynamic_proxy;

//动态代理
public class App {
	public static void main(String[] args) {
		UserService userService = new UserServiceImpl();
		MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);
		// 根据目标对象生成代理对象
		UserService proxy = (UserService) invocationHandler.getProxy();
		// 调用代理对象的方法
		proxy.add();
	}
}