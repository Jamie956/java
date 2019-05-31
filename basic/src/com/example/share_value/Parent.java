package com.example.createobj;

public class Parent {
	int a = 10;
	static int b = 11;
	//静态代码块
	static {
		System.out.println("Parent static block, b = "+b);
		b++;
	}
	//代码块
	{
		System.out.println("Parent block, a = "+a);
		System.out.println("Parent block, b = "+b);
		a++;
		b++;
	}
	//构造函数
	Parent(){
		System.out.println("Parent construct no arg, a = "+a);
		System.out.println("Parent construct no arg, b = "+b);
	}
	Parent(int a){
		System.out.println("Parent construct with arg, a = "+a);
		System.out.println("Parent construct with arg, b = "+b);
	}
	void function() {
		System.out.println("Parent function");
	}
}
