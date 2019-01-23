package com.example;

public class SingletonEager {
	// 在静态初始化器中创建单例实例，保证线程安全
	private static SingletonEager uniqueInstance = new SingletonEager();
	//private 禁止new创建
	private SingletonEager() {
	}
	public static SingletonEager getInstance() {
		return uniqueInstance;
	}
}
