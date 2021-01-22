package com.jamie.demo;

//final 不允许被继承
public final class SingletonEager {
	//static 在静态初始化器中创建单例实例，保证线程安全
	private static SingletonEager uniqueInstance = new SingletonEager();

	//private 禁止外部new
	private SingletonEager() {
	}
	//public 供外部调用
	public static SingletonEager getInstance() {
		return uniqueInstance;
	}
}
