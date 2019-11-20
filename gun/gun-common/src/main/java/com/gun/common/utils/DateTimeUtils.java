package com.gun.common.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Miscellaneous date time utility methods. 
 * <p>This class delivers some simple functionalities that should really
 * be provided by the core Java Calendar, Date, Timestamp and Time classes.
 * <code>
 * 	Date date = DateTimeUtils.toDate("20100402", false);
 *	date = DateTimeUtils.toDate("2010/04/02", false);
 *	date = DateTimeUtils.toDate("2010-04-02", false);
 *	date = DateTimeUtils.toDate("2010/4/2", false);
 *	date = DateTimeUtils.toDate("2010-4-2", false);
 *	date = DateTimeUtils.toDate("20100402140101", false);
 *	date = DateTimeUtils.toDate("2010/04/02 14:01:01", false);
 *	date = DateTimeUtils.toDate("2010-04-02 14:01:01", false);
 *	date = DateTimeUtils.toDate("2010/4/2 14:01:01", false);
 *	date = DateTimeUtils.toDate("2010-4-2 14:01:01", false);
 *	date = DateTimeUtils.toDate("990402", true);
 *	date = DateTimeUtils.toDate("99/04/02", true);
 *	date = DateTimeUtils.toDate("99-04-02", true);
 *	date = DateTimeUtils.toDate("99/4/2", true);
 *	date = DateTimeUtils.toDate("99-4-2", true);
 *	date = DateTimeUtils.toDate("990402140101", true);
 *	date = DateTimeUtils.toDate("99/04/02 14:01:01", true);
 *	date = DateTimeUtils.toDate("99-04-02 14:01:01", true);
 *	date = DateTimeUtils.toDate("99/4/2 14:01:01", true);
 *	date = DateTimeUtils.toDate("99-4-2 14:01:01", true);
 *	date = DateTimeUtils.toDate("20100402140101123", false);
 *	date = DateTimeUtils.toDate("2010/04/02 14:01:01.123", false);
 *	date = DateTimeUtils.toDate("2010-04-02 14:01:01.123", false);
 *	date = DateTimeUtils.toDate("2010/4/2 14:01:01.123", false);
 *	date = DateTimeUtils.toDate("2010-4-2 14:01:01.123", false);
 *</code>
 * @author felixli
 * @since JDK 1.7
 */
public class DateTimeUtils {
		//日期時間格式
		/**日期格式yyyy/MM/dd**/
		public static final String DT_FMT_YYYYMMDD_SLASH 	   		= "yyyy/MM/dd";
		/**日期格式/MM/dd/yyyy**/
		public static final String DT_FMT_MMDDYYYY_SLASH 	   		= "MM/dd/yyyy";
		/**日期格式yyyy-MM-dd**/
		public static final String DT_FMT_YYYYMMDD_DASH 	   		= "yyyy-MM-dd";
		/**日期格式yyyy**/
		public static final String DT_FMT_YYYY                    			= "yyyy";
		/**日期格式yyyyMM**/
		public static final String DT_FMT_YYYYMM							= "yyyyMM";
		/**日期格式MM/dd**/
		public static final String DT_FMT_MMDD								= "MM/dd";
		/**日期格式yyyyMMdd**/
		public static final String DT_FMT_YYYYMMDD 	    			= "yyyyMMdd";
		/**日期格式yyyyMMddHHmmss**/
		public static final String DT_FMT_YYYYMMDDHHMMSS  			= "yyyyMMddHHmmss";
		/**日期格式yyyyMMddHHmmssSSS**/
		public static final String DT_FMT_YYYYMMDDHHMMSSSSS  		= "yyyyMMddHHmmssSSS";
		/**日期格式yyyy/MM/dd HH:mm**/
		public static final String DT_FMT_YYYYMMDDHHMM_SLASH  		= "yyyy/MM/dd HH:mm";
		/**日期格式yyyy-MM-dd HH:mm**/
		public static final String DT_FMT_YYYYMMDDHHMM_DASH  		= "yyyy-MM-dd HH:mm";
		/**日期格式yyyy/MM/dd HH:mm:ss**/
		public static final String DT_FMT_YYYYMMDDHHMMSS_SLASH  	= "yyyy/MM/dd HH:mm:ss";
		/**日期格式yyyy-MM-dd HH:mm:ss**/
		public static final String DT_FMT_YYYYMMDDHHMMSS_DASH  		= "yyyy-MM-dd HH:mm:ss";
		/**日期格式HHmmss**/
		public static final String DT_FMT_HHMMSS					= "HHmmss";
		/**日期格式HHmmssSSS**/
		public static final String DT_FMT_HHMMSSSSS  				= "HHmmssSSS";
		/**日期格式MM/DD/hh:mm**/
		public static final String DATE_FORMAT_MMDDHHMM_STD       	= "MM/DD/hh:mm";
		/**民國年日期格式**/
		public static final String CH_DT_PREFIX 					= "ch_";
		/**民國年日期格式ch_yyyy/MM/dd**/
		public static final String DT_FMT_CH_YYYYMMDD_SLASH 	   	= CH_DT_PREFIX + "yyy/MM/dd";
		/**民國年日期格式ch_yyyy-MM-dd**/
		public static final String DT_FMT_CH_YYYYMMDD_DASH 	   		= CH_DT_PREFIX + "yyy-MM-dd";
		/**民國年日期格式ch_yyyy**/
		public static final String DT_FMT_CH_YYYY                   = CH_DT_PREFIX + "yyy";
		/**民國年日期格式ch_yyyyMM**/
		public static final String DT_FMT_CH_YYYYMM					= CH_DT_PREFIX + "yyyMM";
		/**民國年日期格式ch_yyyyMMdd**/
		public static final String DT_FMT_CH_YYYYMMDD 	    		= CH_DT_PREFIX + "yyyMMdd";
		/**民國年日期格式ch_yyyyMMddHHmmss**/
		public static final String DT_FMT_CH_YYYYMMDDHHMMSS  		= CH_DT_PREFIX + "yyyMMddHHmmss";
		/**民國年日期格式ch_yyyyMMddHHmmssSSS**/
		public static final String DT_FMT_CH_YYYYMMDDHHMMSSSSS  	= CH_DT_PREFIX + "yyyMMddHHmmssSSS";
		/**民國年日期格式ch_yyyy/MM/dd HH:mm**/
		public static final String DT_FMT_CH_YYYYMMDDHHMM_SLASH  	= CH_DT_PREFIX + "yyy/MM/dd HH:mm";
		/**民國年日期格式ch_yyyy-MM-dd HH:mm**/
		public static final String DT_FMT_CH_YYYYMMDDHHMM_DASH  	= CH_DT_PREFIX + "yyy-MM-dd HH:mm";
		/**民國年日期格式ch_yyyy/MM/dd HH:mm:ss**/
		public static final String DT_FMT_CH_YYYYMMDDHHMMSS_SLASH  	= CH_DT_PREFIX + "yyy/MM/dd HH:mm:ss";
		/**民國年日期格式ch_yyyy-MM-dd HH:mm:ss**/
		public static final String DT_FMT_CH_YYYYMMDDHHMMSS_DASH  	= CH_DT_PREFIX + "yyy-MM-dd HH:mm:ss";

		/**
		 * the default date pattern yyyyMMdd for date format.
		 */
		public static final String DEFAULT_DATE_PATTERN			= DT_FMT_YYYYMMDD;
		/**
		 * the default date and time pattern yyyyMMddHHmmss for date format.
		 */
		public static final String DEFAULT_TIMESTAMP_PATTERN	= DT_FMT_YYYYMMDDHHMMSS;

		/**
		 * Field number for get and set indicating the day of the month.
		 */
		public static final int DAY								= Calendar.DAY_OF_MONTH;
		/**
		 * Field number for get and set indicating the hour of the day.
		 */
		public static final int HOUR						 	= Calendar.HOUR_OF_DAY;
		/**
		 * Field number for get and set indicating the minute within the hour.
		 */
		public static final int MINUTE						 	= Calendar.MINUTE;
		/**
		 * Field number for get and set indicating the month.
		 */
		public static final int MONTH						 	= Calendar.MONTH;
		/**
		 * Field number for get and set indicating the second within the minute.
		 */
		public static final int SECOND						 	= Calendar.SECOND;
		/**
		 * Field number for get and set indicating the year.
		 */
		public static final int YEAR						 	= Calendar.YEAR;
		/**
		 * Adds or subtracts the specified amount of time to the given calendar fields year, month and day,  based on the calendar's rules.
		 * <p>For example, to subtract 5 days from the current time of the calendar, you can achieve it by calling: 
		 * <pre>DateTimeUtils.addCalendar(null, 0, 0, -5)</pre>		
		 * @param calendar the given calendar. If its value is null means it is current calendar.
		 * @param year the amount of date to be added to the year field.
		 * @param month the amount of date to be added to the month field.
		 * @param day the amount of date to be added to the day field.
		 * @return a Date
		 */
		public static Date addCalendar(Calendar calendar, int year, int month, int day){
				if (calendar == null) calendar = GregorianCalendar.getInstance();
				if (year != 0) calendar.add(Calendar.YEAR, year);
				if (month != 0) calendar.add(Calendar.MONTH, month);
				if (day != 0) calendar.add(Calendar.DAY_OF_MONTH, day);
				return calendar.getTime();
		}
		/**
		 * Adds or subtracts the specified amount of time to the given calendar fields year, month and day,  based on the calendar's rules.
		 * <p>For example, to subtract 5 days from the current date, you can achieve it by calling: 
		 * <pre>DateTimeUtils.addCalendar(null, 0, 0, -5)</pre>		
		 * @param date the given date. If its value is null means it is current date.
		 * @param year the amount of date to be added to the year field.
		 * @param month the amount of date to be added to the month field.
		 * @param day the amount of date to be added to the day field.
		 * @return a Date
		 */
		public static Date addCalendar(Date date, int year, int month, int day) {
				Calendar cal = GregorianCalendar.getInstance();
				if (date != null) cal.setTime(date);
				return addCalendar(cal, year, month, day);
		}
		/**
		 * Returns the value of the given calendar field.This method throws an exception if any 
		 * calendar fields have out-of-range values. 
		 * @param date The given specific date. If its value is null, means it is current date. 
		 * @param field the given calendar field.
		 * @return the value for the given calendar field. 
		 * <p>The month is between 1-12, the day of the month is between 1-31, 
		 * <p>the hours is between 0-23, the minutes is between 0-59, the seconds is between 0-59.
		 * <p>If the return value is -1 means the specified field is out of range.
		 */
		public static int getCalendar(Date date, int field) {
				try {
					Calendar cal = GregorianCalendar.getInstance();
					if (date != null) cal.setTime(date);
					if (field == MONTH) return cal.get(Calendar.MONTH) + 1;
					else return cal.get(field);
				}catch(Exception e) {
					return -1;
				}
		}
		/**
		 * Returns the value of the given current calendar field
		 * @param field the given calendar field
		 * @return the value for the given calendar field. If the return value is -1 means the specified field is out of range.
		 */
		public static int getCurrentCalendar(int field) {
				return getCalendar(null, field);
		}
		/**
		 * Return the current system date. It doesn't include time.
		 * @return the value of Date.
		 */	
		public static final Date getCurrentDate() {
				Calendar cal = GregorianCalendar.getInstance();
				truncateTime(cal);
				Date current = cal.getTime();
				return current;
		}
		/**
		 * Return the current system time.
		 * @return the value of Time.
		 */
		public static final Time getCurrentTime() {
				Date date = new Date();
				return getTime(date);
		}
		/**
		 * Return the current system timestamp.
		 * @return the value of Timestamp.
		 */
		public static final Timestamp getCurrentTimestamp() {
				return new Timestamp(System.currentTimeMillis());
		}
		/**
		 * Return the days of the given specific year and month. 
		 * @param year the specific year.
		 * @param month the specific month
		 * @return the value of days. If its value is -1 means the specified year or month is out of range.
		 */
		public static int getDaysOfMonth(int year, int month) {
				if (month < 1 || month > 12) return -1;
				
				int secondMonth = 28;
				
				if ((year % 4) == 0) {
					if ((year % 100) == 0 && (year % 400) != 0) secondMonth = 28;
					else secondMonth = 29;
				}else secondMonth = 28;
					
				int[] dayOfMonth = {31, secondMonth, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
					
				return dayOfMonth[month-1];
		}
		/**
		 * Return the time of the given specific date. If its value is null means it is current date.
		 * @param date The given specific date.
		 * @return the value of Time.
		 */	
		public static Time getTime(Date date) {
				Calendar cal = GregorianCalendar.getInstance();
				if ( date != null) cal.setTime(date);
						
				int hour 	= cal.get(Calendar.HOUR_OF_DAY);
				int minute 	= cal.get(Calendar.MINUTE);
				int second 	= cal.get(Calendar.SECOND);
						
				return Time.valueOf(hour+":"+minute+":"+second);
		}
		/**
		 * Return the time of the given specific timestamp. If its value is null means it is current timestamp.
		 * @param timestamp The given specific timestamp.
		 * @return the value of Time.
		 */	
		public static Time getTime(Timestamp timestamp) {
				Date date = (timestamp == null) ? null : new Date(timestamp.getTime());
				return getTime(date);
		}
		/**
		 * Check whether the given date is current date or not.
		 * @param date the given Date.
		 * @return the value of boolean.
		 */
		public static boolean isToday(Date date) {
				if (date == null) return false;
				Date currentDate = getCurrentDate();
				Date otherDate = truncateTime(date);
				return otherDate.equals(currentDate);
		}
		/**
		 * Check whether the given timestamp is current date or not.
		 * @param timestamp the given timestamp
		 * @return the value of boolean.
		 */
		public static boolean isToday(Timestamp timestamp) {
				if (timestamp == null) return false;
				return isToday(new Date(timestamp.getTime()));
		}
		/**
		 * Check whether the current date is a weekly holiday or not.
		 * @return the value of boolean is a weekly holiday or not.
		 */	
		public static boolean isWeeklyHoliday() {
				return isWeeklyHoliday(null);
		}
		/**
		 * Check whether the given specific date is a weekly holiday or not. If the given date is null means it is current date.
		 * @param date the given specific date.
		 * @return the value of boolean is a weekly holiday or not.
		 */	
		public static boolean isWeeklyHoliday(Date date) {
				int dayOfWeek = getCalendar(date, Calendar.DAY_OF_WEEK);
				if (dayOfWeek == 1 || dayOfWeek == 7) return true;
				else return false;
		}
		/**
		 * Parses text from the beginning of the given string to produce a date. 
		 * The method may not use the entire text of the given string.
		 * @param date A String whose beginning should be parsed.
		 * @param pattern the pattern describing the date and time format 
		 * @return A Date parsed from the string. 
		 * @throws ParseException if the beginning of the specified string cannot be parsed.
		 */
		public static Date parseDate(String date, String pattern) throws ParseException {
				SimpleDateFormat format = new SimpleDateFormat(pattern);
				return format.parse(date);
		}
		
		public static String format(Date date, String pattern) {
	        return new SimpleDateFormat(pattern).format(date);
	    }
		/**
		 * Set a specific time to the given specific date.
		 * @param date the given specific date. If its value is null means it is current date.
		 * @param hours the hours between 0-23.
		 * @param mins the minutes between 0-59.
		 * @param secs the seconds between 0-59.
		 * @return a Date includes time.
		 * @throws IllegalArgumentException if the date time value of the specified Date object can't be obtained due to any invalid date values.
		 */
		public static Date toDate(Date date, int hours, int mins, int secs) throws IllegalArgumentException{
				if (hours < 0 || hours > 23 || mins < 0 || mins > 59 || secs < 0 || secs > 59) throw new  IllegalArgumentException(); 
				
				Calendar cal = GregorianCalendar.getInstance();
				
				if ( date != null) cal.setTime(date);
				
				cal.set(Calendar.HOUR_OF_DAY, hours);
				cal.set(Calendar.MINUTE, mins);
				cal.set(Calendar.SECOND, secs);
				
				return cal.getTime();
		}
		/**
		 * Return the Date of the given year, month and day.  
		 * @param year the year minus 1900.
		 * @param month the month between 1-12.
		 * @param day the day of the month between 1-31.
		 * @return a Date.
		 * @throws IllegalArgumentException if the date value of the specified Date object can't be obtained due to any invalid date values.
		 */
		public static Date toDate(int year, int month, int day) throws IllegalArgumentException{
				return toDate(year, month, day, false);
		}
		/**
		 * Return the Date of the given year, month and day.  
		 * @param year the year minus 1900.
		 * @param month the month between 1-12.
		 * @param day the day of the month between 1-31.
		 * @param isChineseDate is chinese date or not
		 * @return a Date.
		 * @throws IllegalArgumentException if the date value of the specified Date object can't be obtained due to any invalid date values.
		 */
		public static Date toDate(int year, int month, int day, boolean isChineseDate) throws IllegalArgumentException{
				if (isChineseDate) year += 1911;
				
				if (year < 0 || month <= 0 || month > 12 || day <= 0 || day > 31) throw new  IllegalArgumentException(); 
				
				Calendar cal = GregorianCalendar.getInstance();
				cal.set(Calendar.YEAR, year);
				cal.set(Calendar.MONTH, month - 1);
				cal.set(Calendar.DAY_OF_MONTH, day);
				
				truncateTime(cal);
				
				return cal.getTime();
		}		
		/**
		 * Return the Date of the given year, month, day, hours, minutes and seconds .  
		 * @param year the year minus 1900.
		 * @param month the month between 1-12.
		 * @param day the day of the month between 1-31.
		 * @param hours the hours between 0-23.
		 * @param mins the minutes between 0-59.
		 * @param secs the seconds between 0-59.
		 * @return a Date.
		 * @throws IllegalArgumentException if the date time value of the specified Date object can't be obtained due to any invalid date values.
		 */
		public static Date toDate(int year, int month, int day, int hours, int mins, int secs) throws IllegalArgumentException{
				if (month < 1 || month > 12 ||
					day < 1 || day > 31 ||
					hours < 0 || hours > 23 ||
					mins < 0 || mins > 59 ||
					secs < 0 || secs > 59) throw new  IllegalArgumentException(); 
						
				Calendar cal = new GregorianCalendar(year, month - 1, day, hours, mins, secs);
				return cal.getTime();
		}
		
		/**
		 * Return the Date of the given year, month, day, hours, minutes and seconds .  
		 * @param year the year minus 1900.
		 * @param month the month between 1-12.
		 * @param day the day of the month between 1-31.
		 * @param hours the hours between 0-23.
		 * @param mins the minutes between 0-59.
		 * @param secs the seconds between 0-59.
		 * @param isChineseDate is chinese date or not
		 * @return a Date.
		 * @throws IllegalArgumentException if the date time value of the specified Date object can't be obtained due to any invalid date values.
		 */
		public static Date toDate(int year, int month, int day, int hours, int mins, int secs, boolean isChineseDate) throws IllegalArgumentException{
				if (isChineseDate) year += 1911;
							
				if (month < 1 || month > 12 ||
					day < 1 || day > 31 ||
					hours < 0 || hours > 23 ||
					mins < 0 || mins > 59 ||
					secs < 0 || secs > 59) throw new  IllegalArgumentException(); 
								
				Calendar cal = new GregorianCalendar(year, month - 1, day, hours, mins, secs);
				return cal.getTime();
		}		
		/**
		 * Return a Date for the given long value. 
		 * The long value of a date should be yyyyMMdd. For example: 20000101.
		 * Or the long value of a timestamp should be yyyyMMddHHmmss. For example: 200001015959.
		 * @param date the long value of the given date. 
		 * @return a Date.
		 * @throws ParseException if the beginning of the specified string cannot be parsed.
		 */ 	
		public static Date toDate(long date) throws ParseException{
				return toDate(date, false);
		}
		/**
		 * Return a Date for the given long value. 
		 * The long value of a date should be yyyyMMdd. For example: 20000101.
		 * Or the long value of a timestamp should be yyyyMMddHHmmss. For example: 200001015959.
		 * @param date the long value of the given date. 
		 * @param isChineseDate is chinese date or not
		 * @return a Date.
		 * @throws ParseException if the beginning of the specified string cannot be parsed.
		 */ 
		public static Date toDate(long date, boolean isChineseDate)  throws ParseException{
				String strDate = date+"";
				String pattern = (strDate.length() > DEFAULT_TIMESTAMP_PATTERN.length()) ? DEFAULT_TIMESTAMP_PATTERN+"SSS" : DEFAULT_TIMESTAMP_PATTERN;
				
				int length = pattern.length();
				int prefixLength = 0;
				if (strDate.length() != DEFAULT_DATE_PATTERN.length() && strDate.length() != pattern.length()) {
						if (strDate.length() < DEFAULT_DATE_PATTERN.length()) length = strDate.length() + (pattern.length()-DEFAULT_DATE_PATTERN.length());
						else length = strDate.length();
						prefixLength = pattern.length() - length;
				}
				String strTimestamp = StringUtils.toFixString(length ,strDate, String.valueOf(StringUtils.ZERO));
				if (prefixLength > 0) strTimestamp = StringUtils.toFixString(prefixLength ,0) + strTimestamp;
				//strTimestamp = StringUtils.replace(strTimestamp, " ","0");
				String strChTimestamp = strTimestamp;
		
				SimpleDateFormat fmt = new SimpleDateFormat();
				fmt.setLenient(false);
						
				Date dt = null;
				for (int i = 0; i < 4; i++) {
						try {
								fmt.applyPattern(pattern);
								dt = fmt.parse(strTimestamp);
								break;
						}catch(Exception e) {
							   strTimestamp = "0"+strTimestamp.substring(0, strTimestamp.length()-(i+1));
						}
				}	
						
				if(isChineseDate) {
					GregorianCalendar cal = new GregorianCalendar();
					//Bug #4224 PROD-[集中登打] 輸入[出生日期]"690229"後,點選"儲存"，變空白未儲存成功
					if(null==dt){
						strChTimestamp = strChTimestamp.replace("/", "");
						String strYear = strChTimestamp.substring(0, 4);
						int year = Integer.valueOf(strYear)+ 1911;
						strChTimestamp = year + strChTimestamp.substring(4);
						dt = fmt.parse(strChTimestamp);
					}
					else{
						cal.setTime(dt);
						cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1911);
						dt = cal.getTime();
					}
				}
						
				return dt;		
		}		
		/**
		 * Return a Date for the given string value. 
		 * The format of the string value of a date could be any legal format defined by Java.
		 * For example:yyyyMMdd or yyyy-MM-dd or yyyy/MM/dd..etc.
		 * @param strDate the string value of the given date. 
		 * @return a Date.
		 * @throws ParseException if the beginning of the specified string cannot be parsed.
		 * @throws NumberFormatException
		 */ 	
		public static Date toDate(String strDate) throws ParseException, NumberFormatException{
				return toDate(strDate, false);
		}
		/**
		 * Return a Date for the given string value. 
		 * The format of the string value of a date could be any legal format defined by Java.
		 * For example:yyyyMMdd or yyyy-MM-dd or yyyy/MM/dd..etc.
		 * @param strDate the string value of the given date. 
		 * @param isChineseDate is chinese date or not
		 * @return a Date.
		 * @throws ParseException if the beginning of the specified string cannot be parsed.
		 * @throws NumberFormatException
		 */ 	
		public static Date toDate(String strDate, boolean isChineseDate) throws ParseException, NumberFormatException{
				if (strDate == null || strDate.trim().length() == 0) return null;
				
				if (!isChineseDate) {
					try {
						SimpleDateFormat fmt = new SimpleDateFormat();
						return fmt.parse(strDate);
					}catch(ParseException pe) {
					}
				}
				
				String[] dateTime = StringUtils.toArray(strDate, " ");
				StringBuffer sb = new StringBuffer("");
				boolean isTime = false;
				for (int i=0; i< dateTime.length; i++) {
					isTime = (i > 0);
					sb.append(formatDateTime(dateTime[i], isChineseDate, isTime));
				}
				
				return toDate(Long.parseLong(sb.toString()), isChineseDate);
		}

		private static String formatDateTime(String datetime, boolean isChineseDate, boolean isTime) {
				char[] c = datetime.toCharArray();
				StringBuffer sb = new StringBuffer("");
				StringBuffer result = new StringBuffer("");
				int index = 0;
				int lastLength = 2;
				for (int i = 0; i < c.length; i++) {
					 if (Character.isDigit(c[i])) sb.append(c[i]);
					 else {
						 if(c[i]=='.') lastLength = 3;
						 int tmpValue = Integer.parseInt(sb.toString());
						 int length = 2;
						 if (index == 0 && !isTime) {
							 length = 4;
							 //if (isChineseDate) tmpValue += 1911;
						 }
						 result.append(StringUtils.toFixString(length, tmpValue));
						 sb = new StringBuffer("");
						 index ++;
					 }
				}
				if (index == 0) result = sb;
				else result.append(StringUtils.toFixString(lastLength, sb.toString(), true, null, String.valueOf(StringUtils.ZERO)));
				
				return result.toString();
		}
		/**
		 * Return a Date for the given Timestamp.
		 * @param timestamp the value of the given timestamp. 
		 * @return a Date. It is not include time.
		 */
		public static Date toDate(Timestamp timestamp) {
				if (timestamp == null) return null;
				return truncateTime(new Date(timestamp.getTime()));
		}
		/**
		 * Return a long value for the given specific Date.
		 * The long value is not the milliseconds. It will be like the format yyyyMMdd.
		 * @param date the given date.
		 * @return a long value.
		 * @throws IllegalArgumentException if the date time value of the specified Date object can't be obtained due to any invalid date values.
		 */		
		public static long toNumeric(Date date) throws IllegalArgumentException{
				return toNumeric(date, false);
		}
		/**
		 * Return a long value for the given specific Date.
		 * The long value is not the milliseconds. It will be like the format yyyyMMdd.
		 * @param date the given date.
		 * @param isChineseDate is chinese date or not
		 * @return a long value.
		 * @throws IllegalArgumentException if the date time value of the specified Date object can't be obtained due to any invalid date values.
		 */		
		public static long toNumeric(Date date, boolean isChineseDate) throws IllegalArgumentException{
				String strDate = toString(date, DEFAULT_DATE_PATTERN, isChineseDate);
				return (strDate==null || strDate.length()==0) ? 0:Long.parseLong(strDate);
		}

		/**
		 * Return a long value for the given specific Timestamp.
		 * The long value is not the milliseconds. It will be like the format yyyyMMddHHmmss.
		 * @param timestamp the given timestamp.
		 * @return a long value.
		 * @throws IllegalArgumentException if the date time value of the specified Date object can't be obtained due to any invalid date values.
		 */		
		public static long toNumeric(Timestamp timestamp) throws IllegalArgumentException{
				return toNumeric(timestamp, false);
		}
		/**
		 * Return a long value for the given specific Timestamp.
		 * The long value is not the milliseconds. It will be like the format yyyyMMddHHmmss.
		 * @param timestamp the given timestamp.
		 * @param isChineseDate is chinese date or not
		 * @return a long value.
		 * @throws IllegalArgumentException if the date time value of the specified Date object can't be obtained due to any invalid date values.
		 */		
		public static long toNumeric(Timestamp timestamp, boolean isChineseDate) throws IllegalArgumentException{
				String strTimestamp = toString(timestamp, DEFAULT_TIMESTAMP_PATTERN, isChineseDate);
				return (strTimestamp == null || strTimestamp.length() == 0) ? 0 : Long.parseLong(strTimestamp);
		}		
		
		/**
		 * Return a formated string value for the given specific Date with a specific format.
		 * @param date the given specific Date.
		 * @param format the specific date format.
		 * @return a formated string value.
		 * @throws IllegalArgumentException if the date time value of the specified Date object can't be obtained due to any invalid date values.
		 */	
		public static String toString(Date date, String format) throws IllegalArgumentException{
				boolean isChineseDate = false;
				if (StringUtils.hasText(format) && format.startsWith(CH_DT_PREFIX)) {
					format = format.substring(CH_DT_PREFIX.length());
					isChineseDate = true;
				}
				return toString(date, format, isChineseDate);
		}
		/**
		 * Return a formated string value for the given specific Date with a specific format.
		 * @param date the given specific Date.
		 * @param format the specific date format.
		 * @param isChineseDate is chinese date or not
		 * @return a formated string value.
		 * @throws IllegalArgumentException if the date time value of the specified Date object can't be obtained due to any invalid date values.
		 */	
		public static String toString(Date date, String format, boolean isChineseDate) throws IllegalArgumentException{
				if (date == null) return null;
				if (isChineseDate) {
					int year = getCalendar(date, YEAR) - 1911;
					String yearPattern = getYearPattern(format);
					format = StringUtils.replace(format,"y","");
					SimpleDateFormat fmt = new SimpleDateFormat(format);
					return StringUtils.toFixString(yearPattern.length(), year) + fmt.format(date);
				} else {
					SimpleDateFormat fmt = new SimpleDateFormat(format);
					return fmt.format(date);
				}	
		}
		
		/**
		 * Return a formated string value for the given specific Timestamp with a specific format.
		 * @param timestamp the given specific Timestamp.
		 * @param format the specific date time format.
		 * @return a formated string value.
		 * @throws IllegalArgumentException if the date time value of the specified Date object can't be obtained due to any invalid date values.
		 */	
		public static String toString(Timestamp timestamp, String format) throws IllegalArgumentException{
				boolean isChineseDate = false;
				if (StringUtils.hasText(format) && format.startsWith(CH_DT_PREFIX)) {
					format = format.substring(CH_DT_PREFIX.length());
					isChineseDate = true;
				}
				return toString(timestamp, format, isChineseDate);
		}
		/**
		 * Return a formated string value for the given specific Timestamp with a specific format.
		 * @param timestamp the given specific Timestamp.
		 * @param format the specific date time format.
		 * @param isChineseDate is chinese date or not
		 * @return a formated string value.
		 * @throws IllegalArgumentException if the date time value of the specified Date object can't be obtained due to any invalid date values.
		 */	
		public static String toString(Timestamp timestamp, String format, boolean isChineseDate) throws IllegalArgumentException{
				if (timestamp == null) return null;
				return toString(new Date(timestamp.getTime()), format, isChineseDate);
		}
		
		/**
		 * Return a Timestamp for the given long value. 
		 * The long value of a timestamp should be yyyyMMddHHmmss. For example: 200001015959.
		 * @param timestamp the long value of the given timestamp. 
		 * @return a Timestamp.
		 * @throws ParseException if the beginning of the specified string cannot be parsed.
		 * @throws NumberFormatException
		 */ 	
		public static Timestamp toTimestamp(long timestamp) throws ParseException, NumberFormatException{
				return toTimestamp(timestamp, false);
		}
		/**
		 * Return a Timestamp for the given long value. 
		 * The long value of a timestamp should be yyyyMMddHHmmss. For example: 200001015959.
		 * @param timestamp the long value of the given timestamp. 
		 * @param isChineseDate is chinese date or not
		 * @return a Timestamp.
		 * @throws ParseException if the beginning of the specified string cannot be parsed.
		 * @throws NumberFormatException
		 */ 	
		public static Timestamp toTimestamp(long timestamp, boolean isChineseDate)  throws ParseException, NumberFormatException{
				return new Timestamp(toDate(timestamp, isChineseDate).getTime());
		}
		
		/**
		 * Return a Timestamp for the given string value. 
		 * The format of the string value of a timestamp could be any legal format defined by Java.
		 * For example:yyyyMMddHHmmss or yyyy-MM-dd HH:mm:ss or yyyy/MM/dd HH:mm:ss..etc.
		 * @param strTimestamp the string value of the given timestamp. 
		 * @return a Timestamp.
		 * @throws ParseException if the beginning of the specified string cannot be parsed.
		 * @throws NumberFormatException
		 */ 	
		public static Timestamp toTimestamp(String strTimestamp) throws ParseException, NumberFormatException{
				return toTimestamp(strTimestamp, false);
		}
		/**
		 * Return a Timestamp for the given string value. 
		 * The format of the string value of a timestamp could be any legal format defined by Java.
		 * For example:yyyyMMddHHmmss or yyyy-MM-dd HH:mm:ss or yyyy/MM/dd HH:mm:ss..etc.
		 * @param strTimestamp the string value of the given timestamp.
		 * @param isChineseDate is chinese date or not
		 * @return a Timestamp.
		 * @throws ParseException if the beginning of the specified string cannot be parsed.
		 * @throws NumberFormatException
		 */ 	
		public static Timestamp toTimestamp(String strTimestamp, boolean isChineseDate) throws ParseException, NumberFormatException{
				return new Timestamp(toDate(strTimestamp, isChineseDate).getTime());
		}
		
		/**
		 * Truncate the time of the given Calendar.
		 * @param calendar the given Calendar.
		 */
		public static void truncateTime(Calendar calendar) {
				if (calendar == null) return;
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);	
		}
		/**
		 * Truncate the time of the given Date.
		 * @param date the given Date
		 * @return the truncated Date
		 */	
		public static Date truncateTime(Date date) {
				if (date == null) return null;
				Calendar cal = GregorianCalendar.getInstance();
				cal.setTime(date);
				truncateTime(cal);
				return cal.getTime();
		}
		
		private static String getYearPattern(String format) {
			if (!StringUtils.hasText(format)) return "0000";
			char[] ch = format.toCharArray();
			StringBuffer sb = new StringBuffer("");
			for (int i = 0; i < ch.length; i++) {
				if (ch[i] == 'y') sb.append("0");
				else break;
			}
			return sb.toString();
		}

		/**
		 * 取得兩個日期相差多少年(單位：年)	
		 * @param d1 YYYYMMDD
		 * @param d1 YYYYMMDD
		 * @return double 年
		 */
		public static double calYear(Date d1, Date d2) {
			double result = 0;
			try {
				Calendar Cal1 = Calendar.getInstance();
				Calendar Cal2 = Calendar.getInstance();
				if ( null != d1 && null != d2 ) {
					Cal1.setTime(d1);
					Cal2.setTime(d2);
					double carYear = Math.abs(Cal1.get(Calendar.YEAR) - Cal2.get(Calendar.YEAR)) ;
					double carMonth = Math.abs(Cal1.get(Calendar.DAY_OF_YEAR) - Cal2.get(Calendar.DAY_OF_YEAR)) ;
					/*無條件捨去到小數點以下2位*/
					double carAge = (Math.floor((carYear + (carMonth/365))*100)/100.0);
					
					result = carAge;
				}
			} catch (Throwable e) {
				return 0;
			}
			return result;
		}
		
		/**
		 * 取得兩個日期相差多少年(單位：年)	
		 * @param d1 日期一
		 * @param d1 日期二
		 * @return int 年
		 */
		public static int dateSubtraction(Date d1, Date d2) {
			int result = 0;
			try {
				Calendar Cal1 = Calendar.getInstance();
				Calendar Cal2 = Calendar.getInstance();
				if ( null != d1 && null != d2 ) {
					Cal1.setTime(d1);
					Cal2.setTime(d2);
					int diffYear = Math.abs(Cal1.get(Calendar.YEAR) - Cal2.get(Calendar.YEAR)) ;
					
					//判斷今年的生日過了沒，沒過就少算一年。
					if ( Cal1.after(Cal2) ) {
						Cal2.set(Calendar.YEAR,Cal1.get(Calendar.YEAR));
						if( Cal1.getTime().getTime() < Cal2.getTime().getTime() ){
							diffYear--;
						}
					} else {
						Cal1.set(Calendar.YEAR,Cal2.get(Calendar.YEAR));
						if( Cal2.getTime().getTime() < Cal1.getTime().getTime() ){
							diffYear--;
						}
					}
					result = diffYear;
				}
			} catch (Throwable e) {
				return 0;
			}
			return result;
		}
		
		/**
		 * 计算两个日期相差多少天
		 * @author felixli
		 * @param fDate
		 * @param oDate
		 * @return
		 */
		public static int daysOfTwo(Date fDate, Date oDate) {

      Calendar aCalendar = Calendar.getInstance();

      aCalendar.setTime(fDate);

      int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

      aCalendar.setTime(oDate);

      int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

      return Math.abs(day2 - day1);

   }
		
		public static void  sasa() throws ParseException{
		  
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		  java.util.Date now = df.parse("2004-03-26 08:31");
		  java.util.Date date=df.parse("2004-03-26 17:30");
		  long l=-(now.getTime()-date.getTime());
		  long day=l/(24*60*60*1000);
		  long hour=(l/(60*60*1000)-day*24);
		  long min=((l/(60*1000))-day*24*60-hour*60);
		  long s=(l/1000-day*24*60*60-hour*60*60-min*60);
		  System.out.println((hour*1.0)+(min*1.0/60));
		  System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
		}
		
		public static void main(String[] args) throws ParseException {
		  sasa();
    }
}
