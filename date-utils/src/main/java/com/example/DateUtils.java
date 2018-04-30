package com.example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtils {
	
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
	
	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH)+1;
	}
	
	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int getWeekOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}
	
	public static int getDayOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_YEAR);
	}
	
	public static int getWeekOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_MONTH);
	}
	
	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	public static Date getLastDays(int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -days);
		return cal.getTime();
	}
	
	public static Date getNextDays(int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static int getPeriodOfDays(Date from, Date to) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(from);
		long startTime = cal.getTimeInMillis();
		cal.setTime(to);
		long endTime = cal.getTimeInMillis();
		long periodOfDays = (endTime - startTime) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(periodOfDays));
	}

	public static List<Date> getContinuousDate(Date from, Date to) {
		int periodOfDays = getPeriodOfDays(from, to) + 1;

		Calendar cal = Calendar.getInstance();
		cal.setTime(from);

		Calendar currentCal = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
		List<Date> dateList = new ArrayList<Date>();

		for (int i = 1; i <= periodOfDays; i++) {
			dateList.add(currentCal.getTime());
			currentCal.add(Calendar.DATE, 1);
		}
		return dateList;
	}
	
}
