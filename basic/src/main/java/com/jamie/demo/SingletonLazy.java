package com.jamie.demo;

//final 不允许被继承
public final class SingletonLazy {
	private static SingletonLazy uniqueInstance;
	//private 不允许外部实例化
	private SingletonLazy() {
	}
	// synchronized确保线程安全
	public static synchronized SingletonLazy getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new SingletonLazy();
		}
		return uniqueInstance;
	}
}
