/********************************************************
 *　撰寫日期：2014/11/26
 *　功能說明：String Utils
 *　撰寫人員：1000367
 ********************************************************
 * 	提案單號		修改日期		修改說明				修改人	*
 ********************************************************
 	1211270015　	2014/11/26	score MQ改WS			1000367	*/

package com.gun.common.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.gun.common.pojo.BeanUtils;


/**
 * This class extends from StringUtils of spring framework.
 * It provides additional miscellaneous string utility methods.
 * 
 * @author Felixli
 * @since JDK 1.7
 */
public class StringUtils extends org.springframework.util.StringUtils {
		/**
		 * default decimal patttern
		 */
		public static String DEFAULT_DECIMAL_PATTERN = "###,###";
		/**
		 * full space hex
		 */
		public static char FULL_SPACE = '\u3000';
		/**
		 * space character
		 */
		public static char SPACE = ' ';
		/**
		 * zero number character
		 */
		public static char ZERO = '0';
		/**
		 * empty space character
		 */
		public static String EMPTY_SPACE = "";
		/**
		 * null text
		 */
		public static String NULL = "NULL";
		
		final static String character = "`1234567890-=qwertyuiop[]\\asdfghjkl;'zxcvbnm,./~!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:\"ZXCVBNM<>?";
		final static String fullchar = "‵１２３４５６７８９０－＝ｑｗｅｒｔｙｕｉｏｐ［］＼ａｓｄｆｇｈｊｋｌ；’ｚｘｃｖｂｎｍ，．／～！＠＃＄％︿＆＊（）＿＋ＱＷＥＲＴＹＵＩＯＰ｛｝｜ＡＳＤＦＧＨＪＫＬ：＂ＺＸＣＶＢＮＭ＜＞？";


		/**
		 * Return a default formatted(###,###) string with zero precision digits
		 * and the comma as the default grouping. 
		 * For example, the number 1234567.12 might be formatted as "1,234,567".
		 * 
		 * @param value
		 * @return
		 */
		public static String formatDecimal(double value) {
				return formatDecimal(value, 0, true);
		}

		/**
		 * Return a default formatted(###,###) string with the given precision
		 * digits.
		 * 
		 * @param value
		 * @param precisionDigits the int value of the given precision digits.
		 * @param isGroupingUsed grouping is used in this format or not. For
		 *            example, the number 1234567 might be formatted as "1,234,567".
		 * @return a formatted string.
		 */
		public static String formatDecimal(double value, int precisionDigits, boolean isGroupingUsed) {
				return formatDecimal(value, DEFAULT_DECIMAL_PATTERN, precisionDigits, isGroupingUsed);
		}
		
		/**
		 * Return a non-group formatted(######) string with the given precision
		 * digits.
		 * 
		 * @param value
		 * @param precisionDigits the int value of the given precision digits.
		 * @return a non-group formatted string.
		 */
		public static String formatDecimal(double value, int precisionDigits) {
				return formatDecimal(value, DEFAULT_DECIMAL_PATTERN, precisionDigits, false);
		}
		
		/**
		 * Return a formatted string with the given format pattern.
		 * 
		 * @param value the double value.
		 * @param format a non-localized pattern string.
		 * @param precisionDigits the int value of the given precision digits.
		 * @param isGroupingUsed grouping is used in this format or not. For
		 *            example, the number 1234567 might be formatted as "1,234,567".
		 * @return a formatted string.
		 */
		public static String formatDecimal(double value, String format, int precisionDigits, boolean isGroupingUsed) {
				try {
						DecimalFormat df = new DecimalFormat(format);
						df.setMaximumFractionDigits(precisionDigits);
						df.setGroupingUsed(isGroupingUsed);
						return df.format(value);
				} catch (Exception e) {
						return value + "";
				}
		}

		/**
		 * Return a default formatted(###,###) string with the comma as the
		 * default grouping.
		 * 
		 * @param value the long value.
		 * @return a formatted string.
		 */
		public static String formatDecimal(long value) {
				return formatDecimal(value, true);
		}

		/**
		 * Return a default formatted(###,###) string.
		 * 
		 * @param value the long value.
		 * @param isGroupingUsed grouping is used in this format or not.
		 * @return a formatted string.
		 */
		public static String formatDecimal(long value, boolean isGroupingUsed) {
				return formatDecimal(value, DEFAULT_DECIMAL_PATTERN, 0, isGroupingUsed);
		}

		/**
		 * Return a formatted string with the given format pattern.
		 * 
		 * @param value the long value.
		 * @param format a non-localized pattern string.
		 * @param precisionDigits the int value of the given precision digits.
		 * @param isGroupingUsed grouping is used in this format or not.
		 * @return a formatted string.
		 */
		public static String formatDecimal(long value, String format, int precisionDigits, boolean isGroupingUsed) {
				try {
						DecimalFormat df = new DecimalFormat(format);
						df.setMaximumFractionDigits(precisionDigits);
						df.setGroupingUsed(isGroupingUsed);
						return df.format(value);
				} catch (Exception e) {
						return value + "";
				}
		}

		/**
		 * Check whether a character is a double bytes or not.
		 * 
		 * @param word the given character.
		 * @return is double bytes or not.
		 */
		public static boolean isDoubleBytes(char word) {
				if (word >= '\u0000' && word <= '\u00ff') return false;
				return true;
		}

		/**
		 * Return a fixed string with the given length. The default format of
		 * the given date will be yyyyMMdd. For Example:the date "2007-01-01"
		 * might be converted as "0020070101" with length=10.
		 * 
		 * @param length the length of the fixed string.
		 * @param dtValue the Date value.
		 * @return a fixed length string.
		 */
		public static String toFixString(int length, Date dtValue) {
				char filledCharacter = (dtValue == null) ? SPACE : ZERO;
				return toFixString(length, dtValue, DateTimeUtils.DEFAULT_DATE_PATTERN, true, String.valueOf(filledCharacter));
		}

		/**
		 * Return a fixed string with the given length. For Example:the number
		 * 1234567 might be converted as "0001234567" with length=10.
		 * 
		 * @param length the length of the fixed string.
		 * @param dtValue the value of the given Date.
		 * @param format the pattern describing the date format.
		 * @return a fixed length string.
		 */
		public static String toFixString(int length, Date dtValue, String format, boolean isLeftPadFields, String filledCharacter) {
				String strDate = null;
				try {
					if (dtValue != null) strDate = DateTimeUtils.toString(dtValue, format);
				} catch (Exception e) { }
				
				return toFixString(length, strDate, isLeftPadFields, null, filledCharacter);
		}
		/**
		 * 
		 * @param length
		 * @param tsValue
		 * @param format
		 * @param isLeftPadFields
		 * @param filledCharacter
		 * @return
		 */
		public static String toFixString(int length, Timestamp tsValue, String format, boolean isLeftPadFields, String filledCharacter) {
			String strTimestamp = null;
			try {
				if (tsValue != null) strTimestamp = DateTimeUtils.toString(tsValue, format);
			} catch (Exception e) { }
			
			return toFixString(length, strTimestamp, isLeftPadFields, null, filledCharacter);
		}		
		/**
		 * Return a right align, append '0' in prefix and fixed string with the
		 * given length. For Example:the number 1234567.0 might be converted as
		 * "0001234567" with length=10.
		 * 
		 * @param length the length of the fixed string.
		 * @param dbValue the double value.
		 * @return a fixed length string.
		 */
		public static String toFixString(int length, double dbValue) {
				return toFixString(length, new Double(dbValue), 0);
		}

		/**
		 * Return a right align, append '0' in prefix and fixed string with the
		 * given length. For Example:the number 1234567 might be converted as
		 * "0001234567" with length=10.
		 * 
		 * @param length the length of the fixed string.
		 * @param intValue the int value.
		 * @return a fixed length string.
		 */
		public static String toFixString(int length, int intValue) {
				return toFixString(length, new Integer(intValue), 0);
		}

		/**
		 * Return a right align, append '0' in prefix and fixed string with the
		 * given length. For Example:the number 1234567 might be converted as
		 * "0001234567" with length=10.
		 * 
		 * @param length the length of the fixed string.
		 * @param longValue the long value.
		 * @return a fixed length string.
		 */
		public static String toFixString(int length, long longValue) {
				return toFixString(length, new Long(longValue), 0);
		}

		/**
		 * Return a fixed string with the given length. For Example:the number
		 * 1234567.12 might be converted as "001234567.12" with length=12 and
		 * precision=2.
		 * 
		 * @param length the length of the fixed string.
		 * @param value the value of the given Number.
		 * @param precision the int value of the given precision digits.
		 * @return a fixed length string.
		 */
		public static String toFixString(int length, Number value, int precision) {
				return toFixString(length, value, null, false, precision, true, null);
		}

		/**
		 * Return a fixed string with the given length.
		 * 
		 * @param length the length of the fixed string.
		 * @param value the value of the given Number.
		 * @param format a non-localized pattern string.
		 * @param isLeftPadFields fill with white spaces in left side if it is
		 *            true. For Example: If isLeftPadFields=true, the number
		 *            1234567 might be converted as " 01234567" with
		 *            format="00000000" and length=10. otherwise, its value
		 *            might become to "01234567 ".
		 * @param precision the int value of the given precision digits.
		 * @param precisionIncludeDot
		 * @param defaultValue the input value will be replaced with the default
		 *            string value if it is null.
		 * @return a fixed length string.
		 */
		public static String toFixString(int length, Number value, String format, boolean isLeftPadFields, int precision, boolean precisionIncludeDot, Number defaultValue) {
				Number tmpValue = (value == null) ? defaultValue : value;

				if (!hasText(format)) {
					int preffixLen = (length - precision);
					if (preffixLen <= 0) preffixLen = length;
					StringBuffer tmpformat = new StringBuffer("");
					tmpformat.append(toFixString(((preffixLen < length) ? preffixLen-1 : preffixLen), "0", String.valueOf(ZERO)));
					if (preffixLen < length) {
						tmpformat.append(".");
						tmpformat.append(toFixString(precision, "0", String.valueOf(ZERO)));
					}
					format = tmpformat.toString();
				}
				tmpValue = (tmpValue == null && format.startsWith("0")) ? new Long(0) : tmpValue;
				String strValue = (tmpValue == null) ? "" : formatDecimal(tmpValue.doubleValue(), format, precision, false);
				if (!precisionIncludeDot) {
					strValue = strValue.replace(".", "");
					strValue = "0"+ strValue;
				}
				return toFixString(length, strValue, isLeftPadFields, "");
		}

		/**
		 * Return a right align, append white spaces in postfix and fixed string
		 * with the given length. For Example:the string "1234567" might be
		 * converted as "1234567   " with length=10.
		 * 
		 * @param length the length of the fixed string.
		 * @param strValue the String value.
		 * @return a fixed length string.
		 */
		public static String toFixString(int length, String strValue) {
				return toFixString(length, strValue, false, null);
		}
		/**
		 * Return a right align, append an assigned filled character in postfix and fixed string
		 * with the given length. For Example:the string "1234567" might be
		 * converted as "1234567000" with length=10 and filled character='0'.
		 * @param length the length of the fixed string.
		 * @param strValue the String value.
		 * @param filledCharacter a character is filled in.
		 * @return
		 */
		public static String toFixString(int length, String strValue, String filledCharacter) {
				return toFixString(length, strValue, false, null, filledCharacter);
		}
		/**
		 * Return a fixed string with the given length.
		 * 
		 * @param length the length of the fixed string.
		 * @param strValue the value of given string.
		 * @param isLeftPadFields fill with white spaces in left side if it is
		 *            true. For Example: If isLeftPadFields=true, the string
		 *            "1234567" might be converted as "   1234567" with length=10.
		 *            otherwise, its value might become to "1234567   ".
		 * @param defaultValue the input value will be replaced with the default
		 *            string value if it is null.
		 * @return a fixed length string.
		 */
		public static String toFixString(int length, String strValue, boolean isLeftPadFields, String defaultValue) {
				return toFixString(length, strValue, isLeftPadFields, defaultValue, String.valueOf(SPACE));
		}
		/**
		 * Return a fixed string with the given length.
		 * 
		 * @param length the length of the fixed string.
		 * @param strValue the value of given string.
		 * @param isLeftPadFields fill with an assigned filled character in left side if it is
		 *            true. For Example: If isLeftPadFields=true, the string
		 *            "1234567" might be converted as "   1234567" with length=10.
		 *            otherwise, its value might become to "1234567   ".
		 * @param defaultValue the input value will be replaced with the default
		 *            string value if it is null.
		 * @param filledCharacter a character is filled in   
		 * @return a fixed length string.
		 */
		public static String toFixString(int length, String strValue, boolean isLeftPadFields, String defaultValue, String filledCharacter) {
				String value = (strValue == null) ? trimWhitespace(defaultValue) : trimWhitespace(strValue);
				if (value == null) value = "";
				
				if (length <= 0) return value;
				
				char[] words = value.toCharArray();

				int startIndex = 0;
				int index = 0;
				String str = "";
				StringBuffer extraWords = new StringBuffer("");
				StringBuffer newWords = new StringBuffer("");
				//boolean isDoubleBytes = false;
				//boolean isFirstGet = true;
				//boolean isFilledCharacherSpace = (filledCharacter == SPACE);
				char chFilledCharacter = (StringUtils.hasText(filledCharacter)) ? filledCharacter.charAt(0) : SPACE;
				boolean isFilledCharacherDoubleBytes = isDoubleBytes(chFilledCharacter);
				while (startIndex < length) {
						if (index < words.length) {
								if (!isLeftPadFields) {
										str = new String(new char[] { words[index++] });
										newWords.append(str);
								} else {
										str = new String(new char[] { words[words.length - (++index)] });
										newWords.insert(0, str);
								}
								//if (isFirstGet) {
								//		isDoubleBytes = isDoubleBytes(str.charAt(0));
								//		isFirstGet = false;
								//}
								startIndex += str.getBytes().length;
						} else {
								for (; startIndex < length; startIndex++) {
										// if (!isRightPadFields) {
										//if (isFilledCharacherSpace) extraWords.append((isDoubleBytes == true) ? FULL_SPACE : SPACE);
										//else extraWords.append(filledCharacter);
										//if (isDoubleBytes) startIndex++;
										str = new String(new char[] {chFilledCharacter});
										extraWords.append(str);
										if (isFilledCharacherDoubleBytes) startIndex++;
								}
						}
				}
				if (!isLeftPadFields) {
						if (startIndex > length) {
								newWords.delete(newWords.length() - 1, newWords.length());
								//if (isFilledCharacherSpace) newWords.append((isDoubleBytes == true) ? FULL_SPACE : SPACE);
								//else newWords.append(filledCharacter);
								newWords.append(filledCharacter);
						}
						newWords.append(extraWords.toString());
				} else newWords.insert(0, extraWords.toString());

				return newWords.toString();
		}

		/**
		 *轉換編碼為Unicode之字串為Big5編碼之字串。
		 * @param str 目標字串。
		 * @return String - 轉換為Big5編碼之字串。
		 */
		public static  String unicode2Big5(String str) {
					   if(str==null) return null;
						try {
								return new String(str.getBytes("ISO-8859-1"),"Big5");
						} catch (Exception e) {}
						return "";
			}
		/**
		 *轉換編碼為Big5之字串為Unicode編碼之字串。
		 * @param str 目標字串。
		 * @return String - 轉換為Unicode編碼之字串。
		 */
		public static String big52Unicode(String str) {
				   if(str==null) return null;
					try  {
							return new String(str.getBytes("Big5"),"ISO-8859-1");
					} 
					catch (Exception e)  { 
					   return ""; 
					}
		}

		/**
		 * 轉換Map物件成成對屬性字串。字串之格式將為"key1=value1,key2=value2.."
		 * @param nameValues Map物件
		 * @return 成對屬性字串
		 */
		public static String toString(Map<String, String> nameValues) {
				return toString(nameValues, null);
		}
		/**
		 * 轉換Map物件成成對屬性字串。
		 * @param nameValues Map物件
		 * @param delimiter
		 * @return
		 */
		public static String toString(Map<String, String> nameValues, String delimiter) {
				if (nameValues == null) return null;
				if (nameValues.isEmpty()) return "";
				Iterator<String> keys = nameValues.keySet().iterator();
				StringBuffer sb = new StringBuffer("");
				String key = null;
				boolean isFirst = true;
				if (!StringUtils.hasText(delimiter)) delimiter = ",";
				while (keys.hasNext()) {
						if (!isFirst) sb.append(delimiter);
						key = keys.next();
						sb.append(key+"="+toText(nameValues.get(key)));
						isFirst = false;
				}
				return sb.toString();
		}
		/**
		 * 轉換成對屬性字串為Map物件。字串之格式須為"key1=value1,key2=value2.."	
		 * @param str 成對屬性之字串。
		 * @return 轉換後之Map物件。
		 */
		public static Map<String, String> toMap(String str) {
				return toMap(str, ",");
		}
		/**
		 *轉換成對屬性字串為Properties物件。字串之格式須為"key1=value1 delimiter key2=value2.."
		 *<pre>例如:欲轉換之字串為"id=A123456789,name=張三"</pre>
		 * @param valueListString 成對屬性之字串。
		 * @param delimiter 區隔屬性之分隔字串。 
		 * @return Properties - 轉換後之Properties物件。
		 */
		public static Map<String, String> toMap(String nameValueString, String delimiter) {
			return toMap(nameValueString, delimiter, null);
		}
		public static Map<String, String> toMap(String nameValueString, String delimiter, String operation) {
				if (!hasText(nameValueString)) return null;
				if (!StringUtils.hasText(operation)) operation = "=";
				StringTokenizer st = new StringTokenizer(nameValueString, delimiter);
				Map<String, String> nvs = new HashMap<String, String>();
				String nameValue = null;
				String name=null;
				String value=null;
				int index = 0;
				while (st.hasMoreTokens()) {
					nameValue = trimWhitespace(st.nextToken());
					index = nameValue.indexOf(operation);
					if(index > 0) {
						name = StringUtils.trimWhitespace(nameValue.substring(0, index));
						value = null;
						try {
							if(index < nameValue.length()-1) {
							   value= trimWhitespace(nameValue.substring(index+1));
							} 
						}catch(Exception e) {}
						nvs.put(name, value);
					}  						
				}
				return nvs;
			}
			/**
			 *轉換字串為List物件。字串之格式須為"value1 delimiter value2.."
			 *例如:欲轉換之字串為"A123456789,張三"。
			 * @param valueString 目標字串。
			 * @param delimiter 分隔字串。 
			 * @return List - 轉換後之List物件。
			 */
			public static List<String> toList(String valueString, String delimiter) {
					if (!hasText(valueString)) return null;
						
					List<String> list = new ArrayList<String>();
					StringTokenizer st = new StringTokenizer(valueString, delimiter);
					String value = null;
					while (st.hasMoreTokens()) {
							value = trimWhitespace(st.nextToken());
							list.add(value);
					}	
					return list; 
			}
			/**
			 *轉換字串為字串陣列。字串之格式須為"value1 delimiter value2.."
			 *例如:欲轉換之字串為"A123456789,張三"。
			 * @param valueString 目標字串。
			 * @param delimiter 分隔字串。 
			 * @return String[] - 轉換後之字串陣列。
			 */
			public static String[] toArray(String valueString, String delimiter) {
					if (!hasText(valueString)) return null;
							
					StringTokenizer st = new StringTokenizer(valueString, delimiter);
					String[] array = new String[st.countTokens()];
					String value = null;
					int index=0;
					while (st.hasMoreTokens()) {
							value = trimWhitespace(st.nextToken());
							array[index++] = value;
					}	
					return array; 	
			}
			/**
			 * 將字串值轉換成文字，若該字串為null,則返回空白
			 * @param value 字串值
			 * @return 文字內容
			 */
			public static String toText(String value) {
					if (value == null) return EMPTY_SPACE;
					else return value;
			}
			/**
			 * 將Double值轉換成文字，若該值為null,則返回空白,預設格式為###,###
			 * @param value Double值
			 * @return　文字內容
			 */
			public static String toText(Double value) {
					return toText(value, 0, true);
			}
			/**
			 * 將Double值轉換成文字，若該值為null,則返回空白,預設格式為###,###
			 * @param value Double值
			 * @param precisionDigits 精確度
			 * @param isGroupingUsed　是否使用分群格式化數值
			 * @return　文字內容
			 */
			public static String toText(Double value, int precisionDigits, boolean isGroupingUsed) {
					return toText(value, DEFAULT_DECIMAL_PATTERN, precisionDigits, isGroupingUsed);
				
			}
			/**
			 * 將Double值轉換成文字，若該值為null,則返回空白
			 * @param value Double值
			 * @param format 數值格式
			 * @param precisionDigits 精確度
			 * @param isGroupingUsed　是否使用分群格式化數值
			 * @return　文字內容
			 */
			public static String toText(Double value, String format, int precisionDigits, boolean isGroupingUsed) {
					if (value == null) return EMPTY_SPACE;
					else return formatDecimal(value, format, precisionDigits, isGroupingUsed);
			}
			/**
			 * 將double值轉換成文字，若該值為null,則返回空白,預設格式為###,###
			 * @param value double值
			 * @return　文字內容
			 */
			public static String toText(double value) {
					return toText(value, 0, true);
			}
			/**
			 * 將double值轉換成文字，若該值為null,則返回空白,預設格式為###,###
			 * @param value double值
			 * @param precisionDigits 精確度
			 * @param isGroupingUsed　是否使用分群格式化數值
			 * @return　文字內容
			 */
			public static String toText(double value, int precisionDigits, boolean isGroupingUsed) {
					return toText(value, DEFAULT_DECIMAL_PATTERN, precisionDigits, isGroupingUsed);
			}
			/**
			 * 將double值轉換成文字，若該值為null,則返回空白
			 * @param value double值
			 * @param format 數值格式
			 * @param precisionDigits 精確度
			 * @param isGroupingUsed　是否使用分群格式化數值
			 * @return　文字內容
			 */
			public static String toText(double value, String format, int precisionDigits, boolean isGroupingUsed) {
					if (value == 0) return EMPTY_SPACE;
					else return formatDecimal(value, format, precisionDigits, isGroupingUsed);
			}
			/**
			 * 將Integer值轉換成文字，若該值為null,則返回空白
			 * @param value Integer值
			 * @return 文字內容
			 */
			public static String toText(Integer value) {
					if (value == null) return EMPTY_SPACE;
					return toText(value.intValue());
			}
			/**
			 * 將int值轉換成文字，若該值為null,則返回空白
			 * @param value int值
			 * @return 文字內容
			 */
			public static String toText(int value) {
					if (value == 0) return EMPTY_SPACE;
					return formatDecimal(value);
			}
			/**
			 * 將Long值轉換成文字，若該值為null,則返回空白
			 * @param value Long值
			 * @return 文字內容
			 */
			public static String toText(Long value) {
					if (value == null) return EMPTY_SPACE;
					return toText(value.longValue());
			}
			/**
			 * 將long值轉換成文字，若該值為null,則返回空白
			 * @param value long值
			 * @return 文字內容
			 */
			public static String toText(long value) {
				if (value == 0) return EMPTY_SPACE;
				return formatDecimal(value);
			}
			/**
			 * 將Date轉換成文字，若該Date為null,則返回空白。預設日期格式為西元年之yyyyMMdd。
			 * @param value Date值
			 * @return 文字內容
			 */
			public static String toText(Date value) {
					return toText(value, DateTimeUtils.DEFAULT_DATE_PATTERN, false);
			}
			/**
			 * 將Date轉換成文字，若該Date為null,則返回空白。預設為西元年。
			 * @param value Date值
			 * @param format 日期格式
			 * @return 文字內容
			 */
			public static String toText(Date value, String format) {
					return toText(value, format, false);
			}
			/**
			 * 將Date轉換成文字，若該Date為null,則返回空白
			 * @param value Date值
			 * @param format 日期格式
			 * @param isChineseDate 是否為民國年
			 * @return 文字內容
			 */
			public static String toText(Date value, String format, boolean isChineseDate) {
					if (value == null) return EMPTY_SPACE;
					try {
						return DateTimeUtils.toString(value, format, isChineseDate);
					}catch(Exception e) {return EMPTY_SPACE;}	
			}
			/**
			 * 將Timestamp轉換成文字，若該Timestamp為null,則返回空白。預設時間戳記格式為西元年之yyyyMMddHHmmss。
			 * @param value Timestamp值
			 * @return 文字內容
			 */	
			public static String toText(Timestamp value) {
					return toText(value, DateTimeUtils.DEFAULT_TIMESTAMP_PATTERN, false);
			}
			/**
			 * 將Timestamp轉換成文字，若該Timestamp為null,則返回空白。預設為西元年。
			 * @param value Timestamp值
			 * @return 文字內容
			 */
			public static String toText(Timestamp value, String format) {
					return toText(value, format, false);
			}
			/**
			 * 將Timestamp轉換成文字，若該Timestamp為null,則返回空白
			 * @param value Timestamp值
			 * @param format 時間戳記日期格式
			 * @param isChineseDate 是否為民國年
			 * @return 文字內容
			 */
			public static String toText(Timestamp value, String format, boolean isChineseDate) {
					if (value == null) return EMPTY_SPACE;
					try {
						return DateTimeUtils.toString(value, format, isChineseDate);
					}catch(Exception e) {return EMPTY_SPACE;}	
			}
			@SuppressWarnings("rawtypes")
      public static String toString(Object obj) {
					if (obj == null) return EMPTY_SPACE;
					Class objClass = obj.getClass();
					if (BeanUtils.isPrimitive(objClass)) {
						if (objClass == BeanUtils.UTIL_DATE_CLASS || objClass == BeanUtils.SQL_DATE_CLASS) return toText((Date)obj);
						else if (objClass == BeanUtils.TIMESTAMP_CLASS) return toText((Timestamp)obj);
						else return obj.toString();
					}else {
				        StringBuffer sb = new StringBuffer("");
				        try {
				        	Field[] fields = BeanUtils.getAllFields(objClass);
				        	if (fields != null) {
				        		for (int i = 0; i < fields.length; i++) {
				        			if(Modifier.isStatic(fields[i].getModifiers())) continue;
				        			Object value = null;
				        			try {
				        				value = BeanUtils.invokeGetter(obj, fields[i].getName());
				        			}catch(Exception e){}
				        			 sb.append("\n\t"+fields[i].getName()+"="+((value == null) ? "" : value.toString()));
				        		}
				        	}
				        }catch(Exception ee) {
				        }
				        return sb.toString();
					}
			}
			/**
			 *將字元轉換成十六進位之字元。
			 * @param value 某字元。
			 * @return String - 轉換後之字元。
			 */
			public static String toHexString(char value) {
					int hb = (value >> 8), lb = (value & 0x00FF);
					return "\\u" + toHexString(hb) + toHexString(lb);
			}
			/**
			 *將整數轉換成十六進位之字元。
			 * @param value 某整數。
			 * @return String - 轉換後之字元。
			 */
			public static String toHexString(int value) {
					char[] var = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
					int hc = (value >> 4), lc = (value & 0x0F);
					return String.valueOf(var[hc]) + String.valueOf(var[lc]);
			}
			/**
			 *將字串轉換成十六進位。
			 * @param value 字串。
			 * @return String - 轉換後之十六進位字串。
			 */
			public static String toHexString(String value) {
					char[] var = value.toCharArray();
					StringBuffer res = new StringBuffer(value.length()*6);
					for (int i=0; i < var.length; i++)
						res.append(toHexString(var[i]));
					return res.toString();
			}
			/**
			 *從一段字串中擷取某固定長度子字串。
			 * @param str 目標字串。
			 * @param startIndex 第幾個字元起。
			 * @param length 取多長之字元。
			 * @return String - 轉換後之固定長度字串。
			 */
			public static String substring(String str,int startIndex,int length) {
					if (str==null) return null;
					return substring(str.toCharArray(), startIndex, length);
			}
			/**
			 *從一段字串中擷取某固定長度子字串。
			 * @param chars 字元陣列。
			 * @param startIndex 第幾個字元起。
			 * @param length 取多長之字元。
			 * @return String - 轉換後之固定長度字串。
			 */
			public static String substring(char[] chars,int startIndex,int length) {
					if (chars==null || chars.length==0) return null;
					int index = startIndex;
					int strLength=0;
					StringBuffer sb = new StringBuffer("");
					while (index < chars.length) {
						if (isDoubleBytes(chars[index]))  strLength += 2;
						else strLength += 1;	
						if (strLength>length) break;
							sb.append(chars[index]);
							index++;
						if(strLength==length) break;
					} 
					return  sb.toString();
			}
			/**
			 * 半形轉全形字
			 * @param str
			 * @return
			 * @return String
			 * @author edwardyen
			 */
			public static String toFullChar(String str){
				  if (!hasText(str)) return "";  
				  char[] chars = str.toCharArray();
				  for(int i=0; i<chars.length; i++){
				        if(chars[i] > '\200') continue; //超過這個應該都是中文字了…      
				        if(chars[i] == 32) {
				        	chars[i] = (char)12288;
				        	continue;
				        }  //半型空白轉成全型空白
				        if(Character.isLetterOrDigit(chars[i])){   
				        	chars[i] = (char)(chars[i] + 65248);  
				        	continue;  
				        }  //是有定義的字、數字及符號
				        int index = character.indexOf(chars[i]);
				        if (index >= 0) {
				        	chars[i] = fullchar.charAt(index);
				        }
				  }
				  return String.valueOf(chars);
			 }

			
	/**
	 * 
	 * isNumeric(for TryCatch)
	 * 
	 * @param number
	 * @throws Exception
	 */
	public static boolean isNumeric(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (NumberFormatException sqo) {
			return false;
		}
	}
  public static String encrPassWord(String passWordStr) throws Exception {  
    String passWord = LotteryConstants.STRING_EMPTY;
    byte[] pic = passWordStr.getBytes();
    for (int i = 0; i < pic.length; i++) {
      pic[i] = (byte) (pic[i] ^ LotteryConstants.XOR_CONST);
    }
    passWord=new String(pic,LotteryConstants.LOTTERY_CODED_FORMAT_UTF_8);
    return passWord;
}
  public static String decryptPassWord(String passWordStr) throws Exception {  
    String passWord = LotteryConstants.STRING_EMPTY;
    byte[] pic = passWordStr.getBytes(LotteryConstants.LOTTERY_CODED_FORMAT_UTF_8);
    for (int i = 0; i < pic.length; i++) {
      pic[i] = (byte) (pic[i] ^ LotteryConstants.XOR_CONST);
    }
    passWord=new String(pic,LotteryConstants.LOTTERY_CODED_FORMAT_UTF_8);
    return passWord;
}
  
  
  public static byte[] toBytes(String s) {
      try {
          return s.getBytes("UTF-8");
      } catch (UnsupportedEncodingException e) {
          throw new RuntimeException(e);
      }
  }

  public static String fromBytes(byte[] bytes) {
      try {
          return new String(bytes, "UTF-8");
      } catch (UnsupportedEncodingException e) {
          throw new RuntimeException(e);
      }
  }
  
  /**
   * Purpose:把首字母是小写字母的转换为大写
   */
  public static String captureName(String name) {
	  char[] cs=name.toCharArray();
	  if ('a'-1 < cs[0] && cs[0] < 'z'+1 ) // 把首字母是小写字母的转换为大写
		  cs[0]-=32;
	  return String.valueOf(cs);
  }
  
  /**
   * Purpose: 查找String中第一个数字的下标并返回, 没有数字返回-1
   */
  public static int indexOfNumber(String str){
	  for (int i=0; i<str.length(); i++) {
		  if (str.charAt(i) >= 48&& str.charAt(i) <= 57){
			  return i;
		  }
	  }
	  return -1;
  }
  public static void main(String[] args) {
    try {
      System.out.println(captureName("sdfsdf"));
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
