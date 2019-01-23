package com.example;

public class SingletonLazy {
	private static SingletonLazy uniqueInstance;
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
