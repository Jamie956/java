package com.demo.utils;

import java.util.Date;
import org.junit.Test;
import com.utils.DateHelper;

public class DateHelperTests {
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
	
	@Test
	public void getDatePastTest() {
		Date date = DateHelper.parseString("2018-05-01 19:15:24");
		Date result = DateHelper.getDataDiff(date, 3);
		System.out.println(result);
	}
	
	@Test
	public void getCurrentWeekTest() {
		int week = DateHelper.getCurrentWeek();
		System.out.println(week);
	}
	
	@Test
	public void getCurrentWeekStrTest() {
		String result = DateHelper.getCurrentWeekStr();
		System.out.println(result);
	}
	
	@Test
	public void getCurrentYearTest() {
		int result = DateHelper.getCurrentYear();
		System.out.println(result);
	}
	
	@Test
	public void getCurrentMonthTest() {
		int result = DateHelper.getCurrentMonth();
		System.out.println(result);
	}
	
	@Test
	public void getCurrentDayTest() {
		int result = DateHelper.getCurrentDay();
		System.out.println(result);
	}
	
	@Test
	public void getUnixTimeTest() {
		int result = DateHelper.getUnixTime("1525255821");
		System.out.println(result);
	}
	
	@Test
	public void formatStringTest() {
		String result = DateHelper.formatString("2018-05-01");
		System.out.println(result);
	}
	
	@Test
	public void formatDateTimeTest() {
		String result = DateHelper.formatDateTime("2018-05-01-23-14");
		System.out.println(result);
	}
	
	@Test
	public void formatDateTimeTest2() {
		String result = DateHelper.formatDateTime(new Date());
		System.out.println(result);
	}
	
}
