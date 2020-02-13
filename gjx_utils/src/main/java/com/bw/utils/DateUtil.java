package com.bw.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @ClassName: DateUtil 
 * @Description: 日期的工具类
 * @author:gjx
 * @date: 2020年1月2日 下午2:59:53
 */
public class DateUtil {

	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//根据生日计算年龄
	public static int getAgeByBirthday(Date birthday) {
		//获得日历控件
		Calendar calendar = Calendar.getInstance();
		//从日历控件中获得当前的年月日
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
	
		calendar.setTime(birthday);
		int birYear = calendar.get(Calendar.YEAR);
		int birMonth = calendar.get(Calendar.MONTH);
		int birDay = calendar.get(Calendar.DAY_OF_MONTH);
		
		int age = year-birYear;
		//如果生日月份大于当前月份  年龄+1
		if(birMonth<month) {
			age++;
		} 
		//如果当前日期大于生日的日期  年龄+1  月份相等
		if(birMonth==month && birDay<day) {
			age++;
		}
		return age;
	}
	
	public static int getAgeByBirthday(String birthday) throws ParseException {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(birthday);
		return getAgeByBirthday(date);
	}
	
	//计算 两个日期相差天数
	public static int getDayNum(Date date1,Date date2) {
		
		//一天多少毫秒
		Long time = 24*60*60*1000L;
		//获得结束时间的毫秒
		long time1 = date2.getTime();
		//获得开始时间的毫秒
		long time2 = date1.getTime();
		//两者相减 = 相差的毫秒
		//相差的毫秒/一天多少毫秒=天数
		return (int)((time1-time2)/time);
	}
	//方法的重载  获得两者相差的天数
	public static int getDayNum(String date1,String date2) throws Exception {
		return getDayNum(sdf.parse(date1), sdf.parse(date2));
	}
	
	//判断是否是当天
	
	//根据给定时间  获得当月的第一天的0时0分0秒
	public static Date getDateByInitMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		//返回设置之后的时间
		return date;
	}
	
	//获得指定时间之前的事件  例如  获得两小时之前的事件
	public static String getIntervalDate(long millis) {
		//获得系统单签时间的毫秒
		Date date = new Date();
		long time1 = date.getTime();
		//获得需要得到的时间的毫秒
		long time = time1-millis;
		//通过获得的毫秒得到时间
		date.setTime(time);
		//将时间转化为字符串
		String format = sdf2.format(date);
		return format;
	}
	
	//通过当前时间获得24小时前的时间
	public static String getDate24Hour() {
		//获得系统当前时间
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		
		calendar.set(year, month, day-1, minute, second);
		Date date = calendar.getTime();
		String format = sdf2.format(date);
		return format;
		
	}
}
