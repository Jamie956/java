package com.utils;

import java.time.LocalDate;

//Java 8 时间特性
public class DateUtilPro {
	public static void main(String[] args) {
		LocalDate now = LocalDate.now();//获取当天格式化日期
//		System.out.println(now);
		
		int year = now.getYear();//获取指定时间的年
		int monthValue = now.getMonthValue();//获取指定时间的月
		int dayOfMonth = now.getDayOfMonth();//获取指定时间的日
		
//		System.out.printf("year = %d, month = %d, day = %d", year, monthValue, dayOfMonth);
		
		LocalDate date = LocalDate.of(2018, 05, 20);//获取指定格式化日期
//		System.out.println(date);
		
//		LocalDate now = LocalDate.now();
		LocalDate someDay = LocalDate.of(2018, 07, 13);
		someDay.equals(now);//判断两个日期是否为同一天
//		System.out.println(someDay.equals(now));
//		if (someDay.equals(now)) {
//			System.out.println("同一天");
//		}
		
	}
}
