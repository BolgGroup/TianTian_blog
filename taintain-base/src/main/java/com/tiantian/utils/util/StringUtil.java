package com.tiantian.utils.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qi_bingo
 */
public class StringUtil {
	/**
	 * 判断输入字符串是否为空（null 或 ""）
	 * 
	 * @param ch
	 *            字符串
	 * @return 是否为空
	 */
	public static boolean isEmpty(String ch) {
		return ch == null || "".equals(ch.trim());
	}

	/**
	 * 获取 空格的脚本
	 * 
	 * @param len
	 *            空格的长度
	 * @return String
	 */
	public static String getBlankChar(int len) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < len; i++) {
			str.append("&nbsp;");
		}
		return str.toString();
	}

	/**
	 * 判断一个字符是否0--9之中
	 * 
	 * @param ch
	 * @return
	 */
	public static boolean isNumberChar(char ch) {
		return ch >= '0' && ch <= '9';
	}

	/**
	 * 转换字符串为日期
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (!isEmpty(str)) {
				return sdf.parse(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 转换字符串为日期
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToDatetime(String str) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (!StringUtil.isEmpty(str)) {
				if (str.length() < 19) {
					str = str.substring(0, 10) + " 00:00:00";
				}
				return sdf.parse(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据参数生成日期
	 * 
	 * @param
	 * @return
	 */
	public static Date getDate(int year, int month, int day, int hour,
			int minute, int second) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ac = "0000" + year;
			String str = ac.substring(ac.length() - 4) + "-";
			ac = "00" + month;
			str += ac.substring(ac.length() - 2) + "-";
			ac = "00" + day;
			str += ac.substring(ac.length() - 2) + " ";
			ac = "00" + hour;
			str += ac.substring(ac.length() - 2) + ":";
			ac = "00" + minute;
			str += ac.substring(ac.length() - 2) + ":";
			ac = "00" + second;
			str += ac.substring(ac.length() - 2);
			return sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将Date转换成String
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		String ret = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (date != null) {
				ret = sdf.format(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 将Date转换成String
	 * 
	 * @param date
	 * @return
	 */
	public static String datetimeToString(Date date) {
		String ret = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (date != null) {
				ret = sdf.format(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 字符串转换为Boolean
	 * 
	 * @param ch
	 *            输入参数
	 * @return Boolean
	 */
	public static Boolean charToBoolean(String ch) {
		if (ch == null) {
			return null;
		}
		return "1".equals(ch);
	}

	/**
	 * 字符串转换为Boolean(是/否)
	 * 
	 * @param ch
	 *            输入参数
	 * @return Boolean
	 */
	public static String charToBool(String ch) {
		String one = "1";
		if (one.equals(ch)) {
			return "是";
		} else {
			return "否";
		}
	}

	/**
	 * 根据对象取得数据库字符串
	 * 
	 * @param obj
	 *            对象参数
	 * @return 数据库字符串
	 */
	public static String objToStr(Object obj) {
		if (obj == null) {
			return null;
		} else if (obj.getClass() == Boolean.class) {
			if ((Boolean) obj) {
				return "1";
			} else {
				return "0";
			}
		} else if (obj.getClass() == Integer.class) {
			return String.valueOf(((Integer) obj).intValue());
		} else if (obj.getClass() == Long.class) {
			return String.valueOf(((Long) obj).longValue());
		} else if (obj.getClass() == Double.class) {
			return String.valueOf(((Double) obj).doubleValue());
		} else if (obj.getClass() == Date.class) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format((Date) obj);
		} else {
			return (String) obj;
		}
	}

	/**
	 * 判断对象的值是否相等
	 * 
	 * @param obj1
	 *            对象参数1
	 * @param obj2
	 *            对象参数2
	 * @return 是否相等
	 */
	public static boolean isValueEquals(Object obj1, Object obj2) {
		if (obj1 == null && obj2 == null) {
			return true;
		} else if (obj1 == null && obj2 != null) {
			return ("".equals(obj2));
		} else if (obj1 != null && obj2 == null) {
			return ("".equals(obj1));
		} else if (obj1.getClass() != obj2.getClass()) {
			return false;
		} else if (obj1.getClass() == Boolean.class) {
			return ((Boolean) obj1).booleanValue() == ((Boolean) obj2)
					.booleanValue();
		} else if (obj1.getClass() == Integer.class) {
			return ((Integer) obj1).intValue() == ((Integer) obj2).intValue();
		} else if (obj1.getClass() == Long.class) {
			return ((Long) obj1).longValue() == ((Long) obj2).longValue();
		} else if (obj1.getClass() == Double.class) {
			return ((Double) obj1).doubleValue() == ((Double) obj2)
					.doubleValue();
		} else if (obj1.getClass() == Date.class) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format((Date) obj1).equals(sdf.format((Date) obj2));
		} else {
			return ((String) obj1).equals((String) obj2);
		}
	}

	/**
	 * 输出字符串为html字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String writeHtml(String str) {
		String ret = "";
		if (str != null) {
			ret = str.replaceAll("&", "&amp;");
			ret = ret.replaceAll("<", "&lt;");
			ret = ret.replaceAll(">", "&gt;");
			ret = ret.replaceAll("\t", "	");
			ret = ret.replaceAll("\r\n", "\n");
			ret = ret.replaceAll("\n", "<br>");
			ret = ret.replaceAll(" ", "&nbsp;");
		}
		return ret;
	}

	/**
	 * 输出字符串为xml字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String writeXml(String str) {
		String ret = "";
		if (str != null) {
			ret = str.replaceAll("&", "&amp;");
			ret = ret.replaceAll("<", "&lt;");
			ret = ret.replaceAll(">", "&gt;");
			ret = ret.replaceAll("\"", "&quot;");
			ret = ret.replaceAll("'", "&apos;");
		}
		return ret;
	}

	/**
	 * @description 获取以今年为起点的过去某一年
	 * @param diff
	 *            0,-1,-2,-3......
	 * @return
	 */
	public static String getLastYear(int diff) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + diff);
		String year = "0000" + cal.get(Calendar.YEAR);
		return year.substring(year.length() - 4);
	}

	/**
	 * @description 获取以今天为起点的过去某一天
	 * @param types
	 *            0---年，1----月，2----日
	 * @param diff
	 *            0,-1,-2,....
	 * @return
	 */
	public static String getDate(int types, int diff) {

		Calendar cal = Calendar.getInstance();
		if (types == 0) {
			cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + diff);
		} else if (types == 1) {
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + diff);
		} else if (types == 2) {
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)
					+ diff);
		}
		String year = "0000" + cal.get(Calendar.YEAR);
		year = year.substring(year.length() - 4);
		String month = "00" + (cal.get(Calendar.MONTH) + 1);
		month = month.substring(month.length() - 2);
		String day = "00" + cal.get(Calendar.DAY_OF_MONTH);
		day = day.substring(day.length() - 2);
		return year + "-" + month + "-" + day;
	}

	public static String getNowDate() {
		return getDate(3, 0);
	}

	/**
	 * 根据html输出字符串
	 * 
	 * @param html
	 * @return
	 */
	public static String htmlWrite(String html) {
		String ret = "";
		if (html != null) {
			ret = html.replaceAll("&amp;", "&");
			ret = ret.replaceAll("&lt;", "<");
			ret = ret.replaceAll("&gt;", ">");
			ret = ret.replaceAll("	", "\t");
			ret = ret.replaceAll("\n", "\r\n");
			ret = ret.replaceAll("<br>", "\n");
			ret = ret.replaceAll("&nbsp;", " ");
		}
		return ret;
	}

	/**
	 * 把字符串的"'转换
	 * 
	 * @param str
	 * @return
	 */
	public static String backslashString(String str) {
		String ret = "";
		if (str != null) {
			ret = str.replaceAll("\\n", " ");
			ret = ret.replaceAll("\"", "\\\\\"");
			ret = ret.replaceAll("'", "\\\\'");
			ret = ret.replaceAll("\\n", " ");
		}
		return ret;
	}

	/**
	 * 对double型数据进行取舍
	 * 
	 * @param v
	 * @param scale
	 *            保留小数点后的位数
	 * @return
	 */
	public static double roundDouble(double v, int scale) {

		if (scale < 0) {
			return v;
		} else {
			BigDecimal decimal = new BigDecimal(Double.toString(v));
			BigDecimal one = new BigDecimal("1");
			return decimal.divide(one, scale, BigDecimal.ROUND_HALF_UP)
					.doubleValue();
		}
	}

	/**
	 * 比较两个日期（date1 是否date2）
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isBeforeDate(String date1, String date2)
			throws Exception {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-mm-dd");
		Date dt1 = sf1.parse(date1);
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-mm-dd");
		Date dt2 = sf2.parse(date2);
		return dt1.before(dt2);

	}

	public static void main(String[] args) {
		try {
			System.out.println(StringUtil.roundDouble(0.125, 2));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	/**
	 * 去空格回车
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {  
         String dest = "";  
         if (str!=null) {  
             Pattern p = Pattern.compile("\\s*|\t|\r|\n");  
             Matcher m = p.matcher(str);  
             dest = m.replaceAll("");  
         }  
         return dest;  
	}  

	
	
}