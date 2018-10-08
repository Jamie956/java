package com.example.ch03;

public class ThreadRun {
	public static void main(String[] args) {
		test01();
	}
	
	public static void test01() {
		Runnable r = () -> System.out.println("Hello!");
		r.run();	
	}
	
}
