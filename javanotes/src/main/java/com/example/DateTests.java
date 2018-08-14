package com.example;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTests {
	public static void main(String[] args) {
		test03();
	}
	
	//日期
	public static void test01() {
		//获取现在的LocalDate
		LocalDate lDate = LocalDate.now();
		System.out.println(lDate);
		//获取指定的日期
		lDate = LocalDate.of(2017, 12, 15);
		System.out.println(lDate);
		//解析日期格式
		lDate = LocalDate.parse("2017-12-15");
		System.out.println(lDate);
		//根据时区获取日期
		lDate = LocalDate.now(ZoneId.systemDefault());
		System.out.println(lDate);
		//获取现在的Date
		Date date = Date.from(Instant.now());
		System.out.println(date);
		//获取时区
		ZoneId defaultZoneId = ZoneId.systemDefault();
		System.out.println(defaultZoneId);
		//根据时区获取LocalDate
		LocalDate localDate = date.toInstant().atZone(defaultZoneId).toLocalDate();
		System.out.println(localDate);
		//根据时区获取Date
		Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
	}
	
	//日期时间,跟LocalDate差不多
	public static void test02() {
		LocalDateTime lDateTime = LocalDateTime.now();
		System.out.println(lDateTime);
		lDateTime = LocalDateTime.of(2017, 12, 15, 11, 30);
		System.out.println(lDateTime);
		lDateTime = LocalDateTime.parse("2017-12-05T11:30:30");
		System.out.println(lDateTime);
		LocalDateTime.now(ZoneId.systemDefault());
		
		Date date = Date.from(Instant.now());
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime =
		date.toInstant().atZone(defaultZoneId).toLocalDateTime();
		Date out = Date.from(localDateTime.atZone(defaultZoneId).toInstant());
		
	}
	
	//时间
	public static void test03() {
		LocalTime time = LocalTime.MIDNIGHT;
		System.out.println(time);
		time = LocalTime.NOON;
		System.out.println(time);
		time = LocalTime.of(12, 12, 45);
		System.out.println(time);
		
		time.plusMinutes(1);
		System.out.println(time);
		time.getMinute();
		System.out.println(time);
		time.minusMinutes(1);
		System.out.println(time);
		
		
		LocalTime lTime = LocalTime.now();
		Instant instant = lTime.atDate(LocalDate.of(2019, 9, 9)).
		atZone(ZoneId.systemDefault()).toInstant();
		Date time2 = Date.from(instant);
	}
	
}
