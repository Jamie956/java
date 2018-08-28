package com.example;

import java.util.Map;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.google.common.net.UrlEscapers;

public class GuavaTests {
	public static void main(String[] args) {
		test04();
	}
	
	//解析url querys并存入Map
	public static void test01() {
		String url = "http://www.baidu.com?name=tom&age=18";
		int paramSeparater = url.indexOf("?");
		String host = url.substring(0, paramSeparater);
		String params = url.substring(paramSeparater + 1);
		Map<String, String> paramMap = Splitter.on("&").withKeyValueSeparator("=").split(params);

		System.out.println(host);
		System.out.println(paramMap);
	}
	
	// 将Map组装成带querys的url
	public static void test02() {
		String url = "http://www.baidu.com";
		Map<String, String> paramMap = Maps.newHashMap();
		paramMap.put("name", "tom");
		paramMap.put("age", "18");
		String rs = url + "?" + Joiner.on("&").withKeyValueSeparator("=").join(paramMap);
		System.out.println(rs);
	}
	
	// LOWER_CAMEL – 以小写字母开头的驼峰命名规则。例如： lowerCamel。这个规则符合表示 Java 属性以及 Java 方法的命名规则。
	// LOWER_HYPHEN – 将单词的小写形式以连接符( - )连接的。例如： lower-hyphen。
	// LOWER_UNDERSCORE – 将单词的小写形式以连接符( _ )连接的。例如： lower_underscore。
	// UPPER_CAMEL – 以大写字母开头的驼峰命名规则。例如： UpperCamel。这个规则符合表示 Java 或 C++ 类名的命名规则。
	// UPPER_UNDERSCORE – 将单词的大写形式以连接符( _ )连接的。例如： UPPER_UNDERSCORE。这个规则符合表示 Java 或
	// C++ 常量的命名规则。
	public static void test03() {
		String rs = "";
		// lowerCamel -> lower-camel
		rs = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "lowerCamel");
		System.out.println(rs);
		// lowerCamel -> lower_camel
		rs = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "lowerCamel");
		System.out.println(rs);
		// lowerCamel -> LowerCamel
		rs = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, "lowerCamel");
		System.out.println(rs);
		// lowerCamel -> LOWER_CAMEL
		rs = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "lowerCamel");
		System.out.println(rs);
	}
	
	// URL 编码
	public static void test04() {
		String rs = UrlEscapers.urlFormParameterEscaper().escape("http://www.baidu.com?name=tom");
		System.out.println(rs);
	}
}
