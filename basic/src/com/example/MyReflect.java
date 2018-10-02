package com.example;

import java.lang.reflect.Constructor;

public class MyReflect {
	public static void main(String[] args) {
		test03();
	}

	public static void test01() {
		MyInstance myInstance = new MyInstance();
		// 获取实例的包名类名
		System.out.println(myInstance.getClass().getName());
	}
	
	public static void test02() {
		Class<?> classInstance = null;
		try {
			// 包名类名 -> Class实例
			classInstance = Class.forName("com.example.MyInstance");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(classInstance.getName());
		
		MyInstance myInstance = null;
		try {
			// Class实例 -> 具体实例
			myInstance = (MyInstance) classInstance.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		myInstance.hello();
	}
	
	public static void test03() {
		Class<?> classInstance = null;
		try {
			classInstance = Class.forName("com.example.MyInstance");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// Class实例 -> 构造函数
		Constructor<?>[] constructors = classInstance.getConstructors();
		
		MyInstance myInstance1 = null;
		MyInstance myInstance2 = null;
		try {
			// 构造函数 -> 具体实例
			myInstance1 = (MyInstance) constructors[0].newInstance();
			myInstance2 =  (MyInstance) constructors[1].newInstance("tom");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(myInstance1);
		System.out.println(myInstance2);
	}
}

class MyInstance {
	private String name;
	
	public MyInstance(){
	}
	public MyInstance(String name){
		this.name = name;
	}
	public void hello() {
		System.out.println("hello world");
	}
	@Override
	public String toString() {
		return "MyInstance [name=" + name + "]";
	}
}
