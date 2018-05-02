package com.demo.test;

import java.util.Date;

import org.junit.Test;

import com.utils.DateHelper;


public class DateTesting {
	@Test
	public void formatDateTest(){
		String now = DateHelper.formatDate(new Date());
		System.out.println(now);//2018-04-30 19:15:24
	}
	
	@Test
	public void formatDateWithPatternTest(){
		String now = DateHelper.formatDate(new Date(), "yyyy");
		System.out.println(now);//2018
	}
	
	@Test
	public void parseStringTest(){
		Date now = DateHelper.parseString("2018-04-30 19:15:24");
		System.out.println(now);//Mon Apr 30 19:15:24 CST 2018
	}
	
	@Test
	public void parseStringWithPatternTest(){
		Date now = DateHelper.parseString("2018-04-30 19:15:24", "yyyy");
		System.out.println(now);//Mon Jan 01 00:00:00 CST 2018
	}
	
	@Test
	public void getWeekStrTest(){
		String weekStr = DateHelper.getWeekStr(new Date());
		System.out.println(weekStr);
	}
	
	@Test
	public void getDateMiliDispersionTest(){
		Date fromDate = DateHelper.parseString("2018-04-20 19:15:24");
		Date toDate = DateHelper.parseString("2018-04-30 19:15:24");
		
		Long dateMiliDispersion = DateHelper.getDateMiliDispersion(fromDate, toDate);
		System.out.println(dateMiliDispersion);
	}
	
	@Test
	public void getDateDiffTest(){
		Date fromDate = DateHelper.parseString("2018-04-20 19:15:24");
		Date toDate = DateHelper.parseString("2018-04-30 19:15:24");
		
		int dateDiff = DateHelper.getDateDiff(fromDate, toDate);
		System.out.println(dateDiff);
	}
}
