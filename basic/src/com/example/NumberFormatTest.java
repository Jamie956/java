package com.example;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatTest {
	public static void main(String[] args) {
		test01();
	}

	public static void test01() {
		Locale locale = new Locale("en", "IN");
		//格式化数字
		NumberFormat nf = NumberFormat.getInstance(locale);
		String rs = nf.format(10000000.99);
		System.out.println(rs);
		//货币格式化
		nf = NumberFormat.getCurrencyInstance(locale);
		rs = nf.format(10340.999);
		System.out.println(rs);
		//百分比格式化
		nf = NumberFormat.getPercentInstance(locale);
		rs = nf.format(10340.999);
		System.out.println(rs);
	}
}
