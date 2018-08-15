package com.example;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigDecimalsTests {
	public static void main(String[] args) {
		test02();
	}
	
	public static void test01() {
		// BigDecimal大小比较
		BigDecimal a = new BigDecimal(5);
		// 大于
		int rs = a.compareTo(new BigDecimal(0));
		System.out.println(rs);
		// 等于
		rs = a.compareTo(new BigDecimal(5));
		System.out.println(rs);
		// 小于
		rs = a.compareTo(new BigDecimal(10));
		System.out.println(rs);
		// 等于
		BigDecimal ax = new BigDecimal(5);
		ax.equals(new BigDecimal(5));
		ax.equals(new BigDecimal(5.00));
	}
	
	public static void test02() {
		BigDecimal a = new BigDecimal("5");
		BigDecimal b = new BigDecimal("7");
		BigDecimal c = new BigDecimal("5.11");
		BigDecimal d = new BigDecimal("7.221");
		
		//加法
		BigDecimal rs = a.add(b);
		System.out.println(rs);
		//减法
		rs = a.subtract(b);
		System.out.println(rs);
		//乘法
		rs = c.multiply(d);
		System.out.println(rs);
		//乘法保留位数
		MathContext returnRules = new MathContext(4, RoundingMode.HALF_DOWN);
		rs = c.multiply(d, returnRules);
		System.out.println(rs);
		//除法,必须设置保留位数
		rs = a.divide(b,10,RoundingMode.HALF_UP);
		System.out.println(rs);
		//余数
		rs = a.remainder(b);//a % b
		System.out.println(rs);
		//n次方
		rs = a.pow(3);
		System.out.println(rs);
		//获取最大值
		rs = a.max(b);
		System.out.println(rs);
		//获取最小值
		rs = a.min(b);
		System.out.println(rs);
		//小数点向左移动
		rs = c.movePointLeft(2);
		System.out.println(rs);
		//小数点向右移动
		rs = a.movePointRight(3);
		System.out.println(rs);
	}
	
}







