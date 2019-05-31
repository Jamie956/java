package com.example;

//静态代码块 -> 创建类 -> 代码块 -> 构造函数
public class ExecuteOrder {
	//静态代码块
	static {
		System.out.println("static block");
	}
	//代码块
	{
		System.out.println("block");
	}
	//构造函数
	ExecuteOrder(){
		System.out.println("construct");
	}
	public static void main(String[] args) {
		new ExecuteOrder();
		System.out.println("====");
		//静态代码块只加载一次
		new ExecuteOrder();
	}
}
