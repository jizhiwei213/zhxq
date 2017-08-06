package com.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static String getDateTime(String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		String time = f.format(new Date());
		return time;
	}

	public static String formatLongDate(String format, long date) {
		SimpleDateFormat f = new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE);
		String time = f.format(new Date(date));
		return time;
	}

	public static long lessQuot(String time1, String time2) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / (24 * 60 * 60 * 1000);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return quot;
	}

	/**
	 * 日期比较，date1 大于 date 2 返回 true ,否则返回 false
	 * @param date1
	 *            时间 1
	 * @param date2
	 *            时间 2
	 * @return
	 */
	public static boolean compareTime(Date date1, Date date2) {
		if (date1 != null && date2 != null) {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			if (date1.getTime() > date2.getTime()) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	public static int compareDay(Date date) {
		Date sysDate = new Date();
		int cday = 0;
		if (date != null) {
			if (sysDate.getTime() > date.getTime()) {// 当统计时间大于系统时间
				long l = sysDate.getTime() - date.getTime();
				long day = l / (24 * 60 * 60 * 1000);
				cday = (int) day;
			}
		}
		return cday;
	}

	public static String compareSystemTime(Date date) {
		String time = "";
		Date sysDate = new Date();
		if (date != null) {
			if (date.getTime() > sysDate.getTime()) {// 当统计时间大于系统时间
				long l = date.getTime() - sysDate.getTime();
				long day = l / (24 * 60 * 60 * 1000);
				long hour = (l / (60 * 60 * 1000) - day * 24);
				long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
				time = hour + "小时" + min + "分";
			}
		}
		return time;
	}

	public static Date formatString(String date, String oldformat) {
		SimpleDateFormat ft = new SimpleDateFormat(oldformat);
		Date formatdate = null;
		try {
			if (date != null && !"".equals(date))
				formatdate = ft.parse(date);
			else
				return null;
		} catch (ParseException e) {
			formatdate = new Date();
			e.printStackTrace();
		}
		return formatdate;
	}

	public static Date formatDate(String hhmmss) {
		SimpleDateFormat ft1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat ft2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date formatdate = null;
		String dateold;
		try {
			dateold = ft1.format(new Date());
			dateold = dateold + " " + hhmmss;
			formatdate = ft2.parse(dateold);
		} catch (ParseException e) {
			formatdate = new Date();
			e.printStackTrace();
		}
		return formatdate;
	}

	public static java.sql.Date formatSQLDate(Date utildate) {
		if (utildate != null) {
			return new java.sql.Date(utildate.getTime());
		}
		return null;
	}

	public static java.sql.Timestamp formatSQLTimestamp(Date utildate) {
		if (utildate != null) {
			return new java.sql.Timestamp(utildate.getTime());
		}
		return null;
	}

	public static Calendar formatUtilDateToCalendar(Date date) {
		Calendar cal = null;
		if (date != null) {
			cal = Calendar.getInstance();
			cal.setTime(date);
		}
		return cal;
	}

	public static String formatUtilDateToString(Date date, String format) {
		String time = null;
		if (date != null && format != null && !"".equals(format)) {
			SimpleDateFormat f = new SimpleDateFormat(format);
			time = f.format(date);
		}
		return time;
	}

	public static Date getLessDate(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + day);
		Date newdate = new Date(cal.getTimeInMillis());
		return newdate;
	}

	public static void main(String[] args) {
		int hh = compareDay(formatString("2012-8-19", "yyyy-MM-dd"));
		System.out.println(DateUtil.getLessDate(new Date(), 3));
	}
}
