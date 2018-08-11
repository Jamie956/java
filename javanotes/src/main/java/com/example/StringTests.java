package com.example;

import java.util.Objects;

public class StringTests {
	public static void main(String[] args) {
//		test1();
//		test2();
//		test3();
//		test4();
//		test5();
		test6();
	}
	//字符串加数字返回字符串
	public static void test1() {
		String firstString = "Test123";
		String secondString = "Test" + 123;
		if (firstString.equals(secondString)) {
		// Both Strings have the same content.
			System.out.println("test1");
		}
	}
	
	//字符串忽略大小写
	public static void test2() {
		String firstString = "Test123";
		String secondString = "TEST123";
		if (firstString.equalsIgnoreCase(secondString)) {
		// Both Strings are equal, ignoring the case of the individual characters.
			System.out.println("test2");
		}
	}
	
	public static void test3() {
		String stringToSwitch = "A";
		switch (stringToSwitch) {
		case "a":
			System.out.println("a");
			break;
		case "A":
			System.out.println("A"); // the code goes here
			break;
		case "B":
			System.out.println("B");
			break;
		default:
			break;
		}
		// 大小写转换
		String string = "This is a Random String";
		String upper = string.toUpperCase();
		String lower = string.toLowerCase();

		// 字符串包含关系
		String str1 = "Hello World";
		String str2 = "Hello";
		String str3 = "helLO";
		System.out.println(str1.contains(str2)); // prints true
		System.out.println(str1.contains(str3));

		// 获取指定字符串的索引
		String s = "this is a long";
		int i = s.indexOf('i');
	}
	
	//避免空指针的比较
	public static void test4() {
		String foo = "baz";
		boolean rs = Objects.equals(foo, "baz");
		System.out.println(rs);
	}
	
	public static void test5() {
		String a = "alpha";
		String b = "alpha";
		String c = new String("alpha");
		//All three strings are equivalent
		System.out.println(a.equals(b) && b.equals(c));
		//Although only a and b reference the same heap object
		System.out.println(a == b);
		System.out.println(a != c);
		System.out.println(b != c);
	}
	
	public static void test6() {
		//分割字符串
		String lineFromCsvFile = "Mickey;Bolton;12345;121216";
		String[] dataCells = lineFromCsvFile.split(";");
		// Result is dataCells = { "Mickey", "Bolton", "12345", "121216"};
		
		//正则表达式分割字符串
		String lineFromInput = "What do you need from me?";
				String[] words = lineFromInput.split("\\s+");
	}
}
