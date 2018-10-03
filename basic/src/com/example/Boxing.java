package com.example;

public class Boxing {
	public static void main(String[] args) {
		test01();
	}
	
	//Boxing 
	public static void test01() {
		int i = 1;
		Integer rs = Integer.valueOf(i);
	}
	
	//Unboxing
	public static void test02() {
		Integer i = new Integer(1);
		int rs = i.intValue();
	}
	
}
