package com.qbao.store.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

public class DateUtil {

	private static final String HOUR_START = " 00:00:00";
	private static final String HOUR_END = " 23:59:59";
	public static final int ONE_SECOND = 1000;
	public static final int MINUTES_PER_HOUR = 60;
	public static final int SECONDS_PER_MINUTE = 60;
	public static final int HOURS_PER_DAY = 24;
	public static final long ONE_MINUTE = SECONDS_PER_MINUTE * ONE_SECOND;
	public static final long ONE_HOUR = MINUTES_PER_HOUR * ONE_MINUTE;
	public static final long ONE_DAY = HOURS_PER_DAY * ONE_HOUR;

	public static Date now() {
		return new Date();
	}

	public static Date tomorrow() {
		return daysAfter(now(), 1);
	}

	public static Date nextMonth() {
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		calendar.add(Calendar.MONTH, 1);
		return calendar.getTime();
	}

	public static Date previousMonth() {
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		calendar.add(Calendar.MONTH, -1);
		return calendar.getTime();
	}

	public static Date daysAfter(Date baseDate, int increaseDate) {
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		calendar.setTime(baseDate);
		calendar.add(Calendar.DATE, increaseDate);
		return calendar.getTime();
	}

	public static Date secondsAfter(Date baseDate, int increaseSeconds) {
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		calendar.setTime(baseDate);
		calendar.add(Calendar.SECOND, increaseSeconds);
		return calendar.getTime();
	}

	public static Date minutesAfter(Date baseDate, int increaseMinutes) {
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		calendar.setTime(baseDate);
		calendar.add(Calendar.MINUTE, increaseMinutes);
		return calendar.getTime();
	}

	public static long daysBetween(Date start, Date end) {
		return (((end.getTime() - start.getTime()) + ONE_HOUR) / (ONE_HOUR * 24));
	}

	public static long minuteBetween(Date start, Date end) {
		return BigDecimal.valueOf(end.getTime() - start.getTime()).divide(BigDecimal.valueOf(ONE_MINUTE), BigDecimal.ROUND_DOWN).longValue();
	}

	public static long secondsBetween(Date start, Date end) {
		return BigDecimal.valueOf(end.getTime() - start.getTime()).divide(BigDecimal.valueOf(ONE_SECOND), BigDecimal.ROUND_DOWN).longValue();
	}

	// 设置小时，并将分秒重置为0
	public static Date setHourTo(Date baseDate, int hour) {
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		calendar.setTime(baseDate);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);
		return calendar.getTime();
	}

	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static int currentHour() {
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static Date monthAfter(Date baseDate, int increaseMonths) {
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		calendar.setTime(baseDate);
		calendar.add(Calendar.MONTH, increaseMonths);
		return calendar.getTime();
	}

	public static Date monthAndDayAfter(Date baseDate, int increaseMonths, int increaseDays) {
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		calendar.setTime(baseDate);
		calendar.add(Calendar.MONTH, increaseMonths);
		calendar.add(Calendar.DAY_OF_MONTH, increaseDays);
		return calendar.getTime();
	}

	/**
	 * 两个日期相隔天数（包含当天）
	 * @param start
	 * @param end
	 * @return
	 */
	public static int betweenDays(Date start, Date end) {
		if (null == start || null == end) {
			return -1;
		}
		long intervalMilli = end.getTime() - start.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}

	public static Date startOneDay(Date date) {
		try {
			String halfFormat = new SimpleDateFormat("yyyy-MM-dd").format(date);
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(halfFormat + HOUR_START);
		} catch (ParseException e) {
			e.printStackTrace();
			return date;
		}
	}

	public static String startOneDayStr(Date date) {
		try {
			String halfFormat = new SimpleDateFormat("yyyy-MM-dd").format(date);
			return halfFormat + HOUR_START;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date endOneDay(Date date) {
		try {
			String halfFormat = new SimpleDateFormat("yyyy-MM-dd").format(date);
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(halfFormat + HOUR_END);
		} catch (ParseException e) {
			e.printStackTrace();
			return date;
		}
	}

	public static boolean isInValidPeroid(Date expiredDate) {
		return (now().getTime() - expiredDate.getTime()) <= 0;
	}

	public static Date hoursAfter(Date baseDate, int increaseHour) {
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		calendar.setTime(baseDate);
		calendar.add(Calendar.HOUR, increaseHour);
		return calendar.getTime();
	}

	public static Date weekStart(Date baseDate) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(baseDate);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return startOneDay(c.getTime());
	}

	public static Date weekEnd(Date baseDate) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(baseDate);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY + 7); // Sunday
		return endOneDay(c.getTime());
	}

	public static String getToday() {
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		return dateFormat(calendar.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 获取前天的日期
	 * yyyyMMdd
	 * @return
	 */
	public static String getLastDay() {
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		calendar.add(Calendar.DAY_OF_MONTH, -2);
		return dateFormat(calendar.getTime(), "yyyyMMdd");
	}

	public static String getPerMonth() {
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		calendar.add(Calendar.MONTH, -1);
		return dateFormat(calendar.getTime(), "yyyy-MM-dd");
	}

	public static String dateFormat(Date date, String pattern) {
		if (null == date || null == pattern || "".equals(pattern.trim())) {
			return "";
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	public static Date parseFormatDate(String date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取今日日期 yyyyMMdd
	 * @return
	 */
	public static String getTodayYYYYMMDD() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(new Date());
	}
	
	/**
	 * 获取今日日期 yyyy-MM-dd
	 * @return
	 */
	public static String getToday_yyyy_MM_dd() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}

	/**
	 * 获取今日月份 yyyyMM
	 * @return
	 */
	public static String getTodayYYYYMM() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		return format.format(new Date());
	}

	/***
	 * 获取昨日日期  yyyyMMdd
	 * @return
	 */
	public static String getYesterdayYYYYMMDD() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(date);
	}

	/**
	 * 获取到第二天0点的秒数
	 * 
	 * @return 秒
	 */
	public static Integer getNowToTomorrowTime() {
		Integer needTime = 0;
		try {
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");// 设置日期格式
			String time = df.format(new Date());
			Calendar c = Calendar.getInstance();
			c.setTime(new SimpleDateFormat("HH:mm:ss").parse(time));
			long curTime = c.getTimeInMillis();
			c.setTime(new SimpleDateFormat("HH:mm:ss").parse("23:59:59"));
			long secTime = c.getTimeInMillis();
			needTime = (int) ((secTime - curTime) / 1000);
			// System.out.println("时间差：" + needTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return needTime;
	}

	/**
	 * 判断当前时间是否在传入的时间区间内
	* @Title: isBetween 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param  @param startDate
	* @param  @param endDate
	* @param  @return
	* @return boolean    返回类型 
	* @throws
	 */
	public static boolean isBetween(Date startDate, Date endDate) {
		boolean result = false;
		Date now = new Date();
		if (startDate.getTime() < now.getTime() && endDate.getTime() > now.getTime()) {
			result = true;
		}
		return result;
	}

	/** 
	 * 得到几天后,再+1天的 时间
	 * @param d 
	 * @param day 
	 * @return 
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + (day + 1));
		Date tempD = now.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String result = df.format(tempD);
		Date rightD = null;
		try {
			rightD = df.parse(result);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rightD;

	}
	
	/**
	 * 字符串日期转Date类型
	* @Title: strToDate 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param  @param strDate
	* @param  @param format    默认 yyyy-MM-dd
	* @param  @return
	* @return Date    返回类型 
	* @throws
	 */
	public static Date strToDate(String strDate ,String format) {
		if(StringUtils.isEmpty(format)){
			format="yyyy-MM-dd";
		}
		 SimpleDateFormat sdf = new SimpleDateFormat(format);  
		 Date date=null;
	     try {
	    	 date= sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
	     return date;
	}

	public static void main(String[] args) throws ParseException {
		Date d = getDateAfter(now(), 10);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		System.out.println(df.format(d));
	}
}
