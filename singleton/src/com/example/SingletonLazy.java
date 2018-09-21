package com.example;

public class SingletonLazy {
	private static SingletonLazy uniqueInstance;
	private SingletonLazy() {
	}
	// 没有加入synchronized关键字的版本是线程不安全的
	public static synchronized SingletonLazy getInstance() {
		// 判断当前单例是否已经存在，若存在则返回，不存在则再建立单例
		if (uniqueInstance == null) {
			uniqueInstance = new SingletonLazy();
		}
		return uniqueInstance;
	}
}
