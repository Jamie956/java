package com.example.design;

public class EagerSingleton {
	public static void main(String[] args) {
		Singleton2 s1 = Singleton2.getInstance();
		Singleton2 s2 = Singleton2.getInstance();
		System.out.println(s1 == s2);// ture,对象地址一样,是同一个对象
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
	}
}

//单例
class Singleton2 {
	//类加载时创建对象
	private static final Singleton2 instance2 = new Singleton2();

	private Singleton2() {
	}
	
	//返回类加载时创建的那个对象
	public static Singleton2 getInstance() {
		return instance2;
	}
}
