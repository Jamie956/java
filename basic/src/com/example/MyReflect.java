package com.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MyReflect {
	public static void main(String[] args) {
		test08();
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
			// Class实例 -> 创建具体实例
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
		// Class实例 -> 获取构造函数
		Constructor<?>[] constructors = classInstance.getConstructors();
		
		MyInstance myInstance1 = null;
		MyInstance myInstance2 = null;
		try {
			// 构造函数 -> 创建具体实例
			myInstance1 = (MyInstance) constructors[0].newInstance();
			myInstance2 =  (MyInstance) constructors[1].newInstance("tom");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(myInstance1);
		System.out.println(myInstance2);
	}
	
	public static void test04() {
		Class<?> classInstance = null;
		try {
			classInstance = Class.forName("com.example.MyInstance");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// Class实例 -> 获取实现的接口
		Class<?>[] interfaces = classInstance.getInterfaces();
		for (int i = 0; i < interfaces.length; i++) {
			System.out.println(interfaces[i].getName());
		}
	}
	
	public static void test05() {
		Class<?> classInstance = null;
		try {
			classInstance = Class.forName("com.example.MyInstance");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// Class实例 -> 成员变量
		Field[] fields = classInstance.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getName());
			// fields[i] -> 权限修饰符
			System.out.println(Modifier.toString(fields[i].getModifiers()));
			// fields[i] -> 类型
			System.out.println(fields[i].getType().getName());
		}
	}
	
	public static void test06() {
		Class<?> classInstance = null;
		try {
			classInstance = Class.forName("com.example.MyInstance");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			// Class实例 -> Method实例
			Method method = classInstance.getMethod("hello");
			// Method实例 -> Method调用
			method.invoke(classInstance.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test07() {
		Class<?> classInstance = null;
		try {
			classInstance = Class.forName("com.example.MyInstance");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			MyInstance myInstance = (MyInstance) classInstance.newInstance();
			Field field = classInstance.getDeclaredField("name");
			// 操作属性
			field.setAccessible(true);
			field.set(myInstance,"cat");
			System.out.println(field.get(myInstance));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test08() {
		MyInstance myInstance = new MyInstance();
		// 获取类加载器类型
		System.out.println(myInstance.getClass().getClassLoader().getClass().getName());
	}
	
}


interface Root{

}

class MyInstance implements Root{
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
