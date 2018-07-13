package com.utils;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

//Java 8 时间特性
public class DateUtilPro {
	public static void main(String[] args) {
		LocalDate now = LocalDate.now();// 获取当天格式化日期
		now.getYear();// 获取指定时间的年
		now.getMonthValue();// 获取指定时间的月
		now.getDayOfMonth();// 获取指定时间的日
		now.plus(1, ChronoUnit.WEEKS);// 获取一个星期后的日期
		LocalDate.of(2018, 05, 20);// 获取指定格式化日期
		now.minus(1, ChronoUnit.YEARS);// 获取一年前的日期
		now.plus(1, ChronoUnit.YEARS);// 获取一年后的日期
		Clock.systemUTC();
		Clock.systemDefaultZone();// 获取系统时区
		now.isLeapYear();// 是否是闰年

		LocalDate together = LocalDate.of(2012, 06, 10);
		LocalDate breakup = LocalDate.of(2016, 06, 10);
		together.equals(breakup);// 判断两个日期是否为同一天
		together.isAfter(breakup);// 判断日期先后
		together.isBefore(breakup);// 判断日期先后

		MonthDay birthday = MonthDay.of(5, 20);
		MonthDay currentMonthDay = MonthDay.from(now);
		currentMonthDay.equals(birthday);// 今天是否是生日

		LocalTime localTime = LocalTime.now();// 获取当前时间
		localTime.plusHours(2);// 增加2小时

		YearMonth.now();// 获取当月天数

		LocalDate a = LocalDate.of(2018, Month.JULY, 20);
		LocalDate b = LocalDate.of(2019, Month.MARCH, 20);
		Period period = Period.between(a, b);
		period.getMonths();// 相差多少个月
		period.getYears();// 相差多少年

		Instant.now();// 获取时间戳

		String dateStr = "2018年05月20日";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
		LocalDate.parse(dateStr, formatter);// 按模板格式解析日期
		now.format(formatter);// 将日期转换模板格式
	}
	
}
