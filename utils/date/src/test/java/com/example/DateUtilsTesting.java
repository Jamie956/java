package com.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateUtilsTesting {
	
	@Test
	public void getYear(){
		int year = DateUtils.getYear(new Date());
		System.out.println(year);
	}
	
	@Test
	public void getMonth(){
		int month = DateUtils.getMonth(new Date());
		System.out.println(month);
	}
	
	@Test
	public void getDay(){
		int day = DateUtils.getDay(new Date());
		System.out.println(day);
	}
	
	@Test
	public void getWeekOfYear(){
		int weekOfYear = DateUtils.getWeekOfYear(new Date());
		System.out.println(weekOfYear);
	}
	
	@Test
	public void getDayOfYear(){
		int dayOfYear = DateUtils.getDayOfYear(new Date());
		System.out.println(dayOfYear);
	}
	
	@Test
	public void getWeekOfMonth(){
		int weekOfMonth = DateUtils.getWeekOfMonth(new Date());
		System.out.println(weekOfMonth);
	}
	
	@Test
	public void getDayOfWeek(){
		int dayOfWeek = DateUtils.getDayOfWeek(new Date());
		System.out.println(dayOfWeek);
	}
	
	@Test
	public void getLastDays(){
		Date date = DateUtils.getLastDays(3);
		System.out.println(date);
	}
	
	@Test
	public void getNextDays(){
		Date date = DateUtils.getNextDays(3);
		System.out.println(date);
	}

	@Test
	public void getContinuousDateTest(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Date from = sdf.parse("20171002");
			Date to = sdf.parse("20171031");
			for (Date str : DateUtils.getContinuousDate(from, to)) {
				System.out.println(str);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
