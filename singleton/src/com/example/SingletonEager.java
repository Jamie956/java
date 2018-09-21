package com.example;

public class SingletonEager {
	// 在静态初始化器中创建单例实例，这段代码保证了线程安全
	private static SingletonEager uniqueInstance = new SingletonEager();
	// Singleton类只有一个构造方法并且是被private修饰的，所以用户无法通过new方法创建该对象实例
	private SingletonEager() {
	}
	public static SingletonEager getInstance() {
		return uniqueInstance;
	}
}
