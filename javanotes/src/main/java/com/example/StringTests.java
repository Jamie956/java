package com.example;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTests {
	public static void main(String[] args) {
		test10();
	}

	public static void test01() {
		String firstString = "Test123";
		String secondString = "Test" + 123;
		// 字符串比较
		if (firstString.equals(secondString)) {
			System.out.println("test1");
		}
	}

	public static void test02() {
		String firstString = "Test123";
		String secondString = "TEST123";
		// 字符串比较忽略大小写
		if (firstString.equalsIgnoreCase(secondString)) {
			System.out.println("test2");
		}
	}

	public static void test03() {
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

	// 避免空指针的比较
	public static void test04() {
		String foo = "baz";
		boolean rs = Objects.equals(foo, "baz");
		System.out.println(rs);
	}

	public static void test06() {
		// 分隔字符串
		String lineFromCsvFile = "Mickey;Bolton;12345;121216";
		String[] dataCells = lineFromCsvFile.split(";");
		// Result is dataCells = { "Mickey", "Bolton", "12345", "121216"};

		// 正则表达式分割字符串
		String lineFromInput = "What do you need from me?";
		String[] words = lineFromInput.split("\\s+");

		// 将数组元素拼凑起来
		String[] elements = { "foo", "bar", "foobar" };
		String singleString = String.join(" + ", elements);

		// StringJoiner组装元素 (分隔符，头部，尾部)
		StringJoiner sj = new StringJoiner(", ", "[", "]");
		sj.add("foo");
		sj.add("bar");
		sj.add("foobar");
		System.out.println(sj); // Prints "[foo, bar, foobar]"

		// StringBuilder连接字符串
		StringBuilder sb = new StringBuilder("a");
		String s = sb.append("b").append("c").toString();

		// concat连接字符串
		String string1 = "Hello ";
		String string2 = "world";
		String string3 = string1.concat(string2);
	}

	public static void test07() {
		// 正则表达式替换
		String s = "spiral metal petal et al.";
		String rs = s.replaceAll("(\\w*etal)", "$1lica");
		System.out.println(rs);
	}
	// org.apache.commons.lang3.StringUtils

	// 获取文本中关键字个数
	public static int countStringInString(String search, String text) {
		Pattern pattern = Pattern.compile(search);
		Matcher matcher = pattern.matcher(text);
		int stringOccurrences = 0;
		while (matcher.find()) {
			stringOccurrences++;
		}
		return stringOccurrences;
	}

	public static void test08() {
		String text = "One fish, two fish, red fish, blue fish";
		System.out.println(countStringInString("fish", text)); // prints 4
		System.out.println(countStringInString(",", text)); // prints 3
	}

	public static void test09() {
		int one = 1;
		String color = "red";
		System.out.print(String.format("One=%d, color=%s%n", one, color));
	}

	public static void test10() {
		//StringBuilder产生重复字符串
		final int n = 10;
		final String s = "hello ";
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < n; i++) {
			builder.append(s);
		}
		String rs = builder.toString();
		System.out.println(rs);
	}

}
