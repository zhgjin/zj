package cn.sky999.common.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 计算任意2个日期内的工作日（没有考虑到国定假日）
 * 
 */
public class WorkDayUtil {

	/**
	 * 计算2个日期之间的相隔天数
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public int getWorkingDay(Calendar d1, Calendar d2) {
		int result = -1;
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int charge_start_date = 0;// 开始日期的日期偏移量
		int charge_end_date = 0;// 结束日期的日期偏移量
		// 日期不在同一个日期内
		int stmp;
		int etmp;
		stmp = 7 - d1.get(Calendar.DAY_OF_WEEK);
		etmp = 7 - d2.get(Calendar.DAY_OF_WEEK);
		if (stmp != 0 && stmp != 6) {// 开始日期为星期六和星期日时偏移量为0
			charge_start_date = stmp - 1;
		}
		if (etmp != 0 && etmp != 6) {// 结束日期为星期六和星期日时偏移量为0
			charge_end_date = etmp - 1;
		}
		result = (getDaysBetween(this.getNextMonday(d1), this.getNextMonday(d2)) / 7) * 5 + charge_start_date
				- charge_end_date;
		return result;
	}
	public int getDaysBetween(Calendar d1, Calendar d2) {
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}
	public String getChineseWeek(Calendar date) {
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

		int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
		return dayNames[dayOfWeek - 1];
	}
	/**
	 * 获得日期的下一个星期一的日期
	 * 
	 * @param date
	 * @return
	 */
	public Calendar getNextMonday(Calendar date) {
		Calendar result = null;
		result = date;
		do {
			result = (Calendar) result.clone();
			result.add(Calendar.DATE, 1);
		} while (result.get(Calendar.DAY_OF_WEEK) != 2);
		return result;
	}
	/**
	 * 计算休息日
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public int getHolidays(Calendar d1, Calendar d2) {
		return this.getDaysBetween(d1, d2) - this.getWorkingDay(d1, d2);
	}

	public static int getWorkDays(String beginStr, String endStr) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = sdf.parse(beginStr);
		Date endDate = sdf.parse(endStr);
		if (beginDate.after(endDate))
			throw new Exception("日期范围非法");
		// 总天数
		int days = (int) ((endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000)) + 1;
		// 总周数，
		int weeks = days / 7;
		int rs = 0;
		// 整数周
		if (days % 7 == 0) {
			rs = days - 2 * weeks;
		} else {
			Calendar begCalendar = Calendar.getInstance();
			Calendar endCalendar = Calendar.getInstance();
			begCalendar.setTime(beginDate);
			endCalendar.setTime(endDate);
			// 周日为1，周六为7
			int beg = begCalendar.get(Calendar.DAY_OF_WEEK);
			int end = endCalendar.get(Calendar.DAY_OF_WEEK);
			if (beg > end) {
				rs = days - 2 * (weeks + 1);
			} else if (beg < end) {
				if (end == 7) {
					rs = days - 2 * weeks - 1;
				} else {
					rs = days - 2 * weeks;
				}
			} else {
				if (beg == 1 || beg == 7) {
					rs = days - 2 * weeks - 1;
				} else {
					rs = days - 2 * weeks;
				}
			}
		}
		return rs;
	}
}
