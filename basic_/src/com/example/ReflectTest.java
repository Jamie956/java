package com.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectTest {
	public static void main(String[] args) {
		test06();
	}
	
	//获取实例的class
	public static void test01() {
		// 实例 -> 包名类名
		System.out.println(new Person().getClass().getName());
	}
	
	//class name 获取class
	public static Class<?> getClazz() {
		Class<?> clazz = null;
		try {
			clazz = Class.forName("com.example.MyInstance");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clazz;
	}
	
	//实例化一个class
	public static void test02() {
		Class<?> clazz = getClazz();
		
		Person myInstance = null;
		try {
			// Class -> 实例
			myInstance = (Person) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		myInstance.hello();
	}
	
	//构造函数实例化一个class
	public static void test03() {
		Class<?> clazz = getClazz();
		
		Constructor<?>[] constructors = clazz.getConstructors();
		
		Person a = null;
		Person b = null;
		try {
			a = (Person) constructors[0].newInstance();
			b =  (Person) constructors[1].newInstance("tom");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(a);
		System.out.println(b);
	}
	
	//获取class成员变量
	public static void test05() {
		Class<?> clazz = getClazz();
		
		// Class实例 -> 成员变量
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getName());
			// fields[i] -> 权限修饰符
			System.out.println(Modifier.toString(fields[i].getModifiers()));
			// fields[i] -> 类型
			System.out.println(fields[i].getType().getName());
		}
	}
	
	//调用方法
	public static void test06() {
		Class<?> clazz = getClazz();
		try {
			// Class实例 -> Method实例
			Method method = clazz.getMethod("hello");
			// Method实例 -> Method调用
			method.invoke(clazz.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//修改变量访问权限
	public static void test07() {
		Class<?> clazz = getClazz();
		
		try {
			Person myInstance = (Person) clazz.newInstance();
			Field field = clazz.getDeclaredField("name");
			// 操作属性
			field.setAccessible(true);
			field.set(myInstance,"cat");
			System.out.println(field.get(myInstance));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 获取类加载器类型
	public static void test08() {
		Person myInstance = new Person();
		System.out.println(myInstance.getClass().getClassLoader().getClass().getName());
	}
	
}

class Person{
	private String name;
	
	public Person(){
	}
	public Person(String name){
		this.name = name;
	}
	public void hello() {
		System.out.println("hello world");
	}
	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
}
