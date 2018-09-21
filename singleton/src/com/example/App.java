package com.example;

public class App {
	public static void main(String[] args) {
		test02();
	}

	public static void test01() {
		SingletonEager a = SingletonEager.getInstance();
		SingletonEager b = SingletonEager.getInstance();
		System.out.println(a == b);// true，同一地址
	}
	public static void test02() {
		SingletonLazy a = SingletonLazy.getInstance();
		SingletonLazy b = SingletonLazy.getInstance();
		System.out.println(a == b);// true，同一地址
	}
}
