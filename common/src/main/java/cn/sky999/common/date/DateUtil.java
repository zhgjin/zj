package cn.sky999.common.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	@SuppressWarnings("static-access")
	public static int getYear(){
		Calendar cal = Calendar.getInstance();
		return cal.get(cal.YEAR);
	}
	
	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null)
			return "";
		if (pattern == null)
			pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}

	/**
	 * 日期转yyyy-MM-dd字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		if (date == null)
			return formatDate(new Date(), "yyyy-MM-dd");
		return formatDate(date, "yyyy-MM-dd");
	}

	/**
	 * 日期转yyyy-MM-dd HH:mm:ss字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * yyyy-MM-dd格式字符串转日期
	 * 
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String dateStr, String pattern) {
		try {
			if ((dateStr == null) || (dateStr.equals(""))) {
				return null;
			}

			if (pattern == null) {
				pattern = "yyyy-MM-dd";
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			return simpleDateFormat.parse(dateStr);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * yyyy-MM-dd HH:mm:ss格式字符串转日期
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parseDateTime(String dateStr) {
		try {
			return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
		}
		return null;
	}

	public static Date add(Date date, int field, int amount) {
		if (date == null) {
			date = new Date();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);

		return cal.getTime();
	}

	/**
	 * 指定天数（前）后的时间
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addDay(Date date, int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}

	/**
	 * 指定月数（前）后的时间
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addMonth(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
	}

	/**
	 * 指定年数（前）后的时间
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date addYear(Date date, int amount) {
		return add(date, Calendar.YEAR, amount);
	}

	/**
	 * 当月月初
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 当月月末
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}
	/**
	 * 获取年月日
	 * @param cal
	 * @return
	 */
	public static String getYMDString(Calendar cal)
	{
		if (cal == null)
			return null;
		return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
		
	}
	/**
	 * 获取到当天凌晨时间
	 * @return
	 */
	public static Date getTimeOf12() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return  cal.getTime();
    }

	/**
	 * 获取两个日期相差天数
	 * @param startCal0
	 * @param toCal0
	 * @return
	 */
	public static int getDayNumBetween2Date(Calendar startCal0, Calendar toCal0)
	{
		if (startCal0 == null || toCal0 == null)
			return -1;
		Calendar startCal = Calendar.getInstance();
		Calendar toCal = Calendar.getInstance();
		startCal.setTimeInMillis(startCal0.getTimeInMillis());
		toCal.setTimeInMillis(toCal0.getTimeInMillis());

		startCal.set(Calendar.HOUR, 00);
		startCal.set(Calendar.MINUTE, 00);
		startCal.set(Calendar.SECOND, 000);
		toCal.set(Calendar.HOUR, 00);
		toCal.set(Calendar.MINUTE, 00);
		toCal.set(Calendar.SECOND, 000);
		long dateRange = toCal.getTimeInMillis() - startCal.getTimeInMillis();
		long oneDay = 1000 * 3600 * 24;
		int diffdays = (int) (dateRange / oneDay);
		return diffdays;
	}
	

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    public static long futureDays(String date) throws Exception{
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date  currdate = format.parse(date);
        long t =  currdate.getTime() - new Date().getTime();
        return t / (24 * 60 * 60 * 1000);
    }
}
