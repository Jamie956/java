package com.example.uti;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateUtil {
	
	//获取指定日期的年份
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
	
	//获取指定日期的月份
	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH)+1;
	}
	
	//获取指定日期一个月的第几天
	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	//获取指定日期一年的第几周
	public static int getWeekOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}
	
	//获取指定日期一年的第几天
	public static int getDayOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_YEAR);
	}
	
	//获取指定日期一月的第几周
	public static int getWeekOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_MONTH);
	}
	
	//获取指定日期一周的第几天
	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	//获取过去x天的日期
	public static Date getLastDays(int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -days);
		return cal.getTime();
	}
	
	//获取将来x天的日期
	public static Date getNextDays(int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	//获取两个日期相隔多少天
	public static int getPeriodOfDays(Date from, Date to) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(from);
		long startTime = cal.getTimeInMillis();
		cal.setTime(to);
		long endTime = cal.getTimeInMillis();
		long periodOfDays = (endTime - startTime) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(periodOfDays));
	}
	
	//获取一段时间内的所有日期
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
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Date from = sdf.parse("20171002");
			Date to = sdf.parse("20171031");
			for (Date str : DateUtil.getContinuousDate(from, to)) {
				System.out.println(str);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
