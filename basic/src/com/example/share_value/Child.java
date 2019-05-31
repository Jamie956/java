package com.example.createobj;

public class Child extends Parent {
	int x = 10;
	static int y = 11;
	//静态代码块
	static {
		System.out.println("Child static block, y = "+y);
		y++;
	}
	//代码块
	{
		System.out.println("Child block, x = "+x);
		System.out.println("Child block, y = "+y);
		y++;
		x++;
	}
	//构造函数
	Child(){
		System.out.println("Child construct no arg, x = "+x);
		System.out.println("Child construct no arg, y = "+y);
	}
	void function() {
		System.out.println("Child function");
	}
}