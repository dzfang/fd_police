package com.soft.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;

import com.soft.manager.Constants;

/**
 * 工具类
 * 
 * @version 1.0
 */
public class Utils {

	/** 日期格式标记--1：yyyy-MM-dd HH:mm:ss */
	public static final String DATE_FORMAT_1 = "1";

	/** 日期格式标记--2：yyyyMM */
	public static final String DATE_FORMAT_2 = "2";

	/** 日期格式标记--3：HHmmssSSS */
	public static final String DATE_FORMAT_3 = "3";

	/** 日期格式标记--4：yyyy-MM-dd */
	public static final String DATE_FORMAT_4 = "4";

	/** 日期格式标记--4：yyyyMMddHHmmssSSS */
	public static final String DATE_FORMAT_5 = "5";

	/**
	 * 判断指定的字符串是null或""
	 * 
	 * @param obj
	 *            检查的字符串
	 * @return boolean
	 */
	public static Boolean isEmptyString(String obj) {
		if (obj == null || "".equals(obj.trim()))
			return true;
		return false;
	}

	/**
	 * 判断指定字符串不是null或""
	 * 
	 * @param obj
	 *            检查的字符串
	 * @return boolean
	 */
	public static boolean isNotEmptyString(String obj) {
		if (obj != null && !"".equals(obj.trim()))
			return true;
		return false;
	}

	/**
	 * 判断指定的值不是null
	 * 
	 * @param obj
	 *            检查的字符串
	 * @return boolean
	 */
	public static Boolean isNotNull(Object obj) {
		if (obj != null)
			return true;
		return false;
	}

	/**
	 * 判断指定的值是null
	 * 
	 * @param obj
	 *            检查的字符串
	 * @return boolean
	 */
	public static Boolean isNull(Object obj) {
		if (obj == null)
			return true;
		return false;
	}

	/**
	 * 获取当前系统时间
	 * 
	 * @param 格式化标记
	 *            (1：yyyy-MM-dd HH:mm:ss 2：yyyyMM 3：yyyyMMddHHmmss 4:yyyy-MM-dd)
	 * @return
	 */
	public static String getSystemDate(String formatFlag) {
		// 日期格式
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		// 年月
		if (Utils.DATE_FORMAT_2.equals(formatFlag)) {
			dateFormat = "yyyyMM";
		}
		// 时分秒毫秒
		else if (Utils.DATE_FORMAT_3.equals(formatFlag)) {
			dateFormat = "HHmmssSSS";
		}
		// 年-月-日
		else if (Utils.DATE_FORMAT_4.equals(formatFlag)) {
			dateFormat = "yyyy-MM-dd";
		}
		// 年月日时分秒
		else if (Utils.DATE_FORMAT_5.equals(formatFlag)) {
			dateFormat = "yyyyMMddHHmmssSSS";
		}
		// 设置日期格式
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);

		// 获取当前系统时间
		Timestamp sysTime = new Timestamp(System.currentTimeMillis());
		return df.format(sysTime);
	}

	/**
	 * 获取Timestamp类型时间
	 * 
	 * @param 格式化标记
	 *            (1：yyyy-MM-dd HH:mm:ss 2：yyyyMM 3：yyyyMMddHHmmss 4:yyyy-MM-dd)
	 * @return
	 */
	public static Timestamp getTimestampDate(String formatFlag) {
		String dateTime = getSystemDate(formatFlag);
		if (isNotEmptyString(dateTime)) {
			return Timestamp.valueOf(dateTime);
		} else {
			return new Timestamp(System.currentTimeMillis());
		}
	}

	/**
	 * 
	 * getStartDateQuery(根据所点击的按钮设定查询条件开始日期)
	 * 
	 * @param dateQuery
	 *            页面所得到的按钮的值 @return Timestamp 开始日期 @exception
	 */
	public static Timestamp getStartDateQuery(String dateQuery) {
		Timestamp startDateQuery = null;
		// 设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		switch (dateQuery) {
		case "today":
			Calendar today = Calendar.getInstance();
			startDateQuery = Timestamp.valueOf(df.format(today.getTimeInMillis()));
			break;
		case "yesterday":
			Calendar yesterday = Calendar.getInstance();
			yesterday.add(Calendar.DATE, -1);
			startDateQuery = Timestamp.valueOf(df.format(yesterday.getTimeInMillis()));
			break;
		case "week":
			Calendar week = Calendar.getInstance();
			week.set(Calendar.DAY_OF_WEEK, 2);
			startDateQuery = Timestamp.valueOf(df.format(week.getTimeInMillis()));
			break;
		case "month":
			Calendar month = Calendar.getInstance();
			month.add(Calendar.MONTH, 0);
			month.set(Calendar.DAY_OF_MONTH, 1);
			startDateQuery = Timestamp.valueOf(df.format(month.getTimeInMillis()));
			break;
		case "day7":
			Calendar day7 = Calendar.getInstance();
			day7.add(Calendar.DATE, -6);
			startDateQuery = Timestamp.valueOf(df.format(day7.getTimeInMillis()));
			break;
		case "day30":
			Calendar day30 = Calendar.getInstance();
			day30.add(Calendar.DATE, -29);
			startDateQuery = Timestamp.valueOf(df.format(day30.getTimeInMillis()));
			break;
		}
		return startDateQuery;
	}

	/**
	 * 
	 * getEndDateQuery(根据所点击的按钮设定查询条件结束日期)
	 * 
	 * @param dateQuery
	 *            页面所得到的按钮的值 @return Timestamp 结束日期 @exception
	 */
	public static Timestamp getEndDateQuery(String dateQuery) {
		Timestamp endDateQuery = null;
		// 设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		switch (dateQuery) {
		case "yesterday":
			Calendar yesterday = Calendar.getInstance();
			yesterday.add(Calendar.DATE, -1);
			endDateQuery = Timestamp.valueOf(df.format(yesterday.getTimeInMillis()));
			break;
		case "today":
		case "week":
		case "month":
		case "day7":
		case "day30":
			endDateQuery = Timestamp.valueOf(df.format(Calendar.getInstance().getTimeInMillis()));
			break;
		}
		return endDateQuery;
	}

	/**
	 * 解码经js编码后的字符串
	 * 
	 * @param value
	 * @return
	 */
	public static String URLDecode(String value) {
		if (value == null || value.trim() == "") {
			return "";
		}
		try {
			return (URLDecoder.decode(value, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 将Object转换为String
	 * 
	 * @param value
	 * @return
	 */
	public static String toString(Object value) {
		if (value == null) {
			return "";
		}
		return value.toString();
	}

	/**
	 * 读取properties文件
	 * 
	 * @param fileName
	 *            properties文件名
	 * @return
	 */
	public static Properties getProperties(String fileName) {
		Properties p = new Properties();
		try {
			InputStream in = Utils.class.getResourceAsStream(fileName);
			p.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return p;
	}

	/**
	 * 将String转换为Date
	 * 
	 * @param format
	 * @param value
	 * @return
	 */
	public static Date stringToDate(DateFormat format, String value) {
		Date date = null;
		try {
			date = format.parse(value);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * stringToDate(根据输入的格式，将字符串转为日期) @param formatFlag 日期格式 @param value
	 * 日期字符串 @return Date @exception
	 */
	public static Date stringToDate(String formatFlag, String value) {
		if (Utils.isEmptyString(value)) {
			return null;
		}
		if (value.trim().length() == 8) {
			value = value.substring(0, 4) + "-" + value.substring(5, 6) + "-" + value.substring(7, 8);
		}
		Date date = null;
		try {
			// 日期格式
			String dateFormat = "yyyy-MM-dd HH:mm:ss";
			// 年月
			if (Utils.DATE_FORMAT_2.equals(formatFlag)) {
				dateFormat = "yyyyMM";
			}
			// 时分秒毫秒
			else if (Utils.DATE_FORMAT_3.equals(formatFlag)) {
				dateFormat = "HHmmssSSS";
			}
			// 年-月-日
			else if (Utils.DATE_FORMAT_4.equals(formatFlag)) {
				dateFormat = "yyyy-MM-dd";
			}
			// 年月日时分秒
			else if (Utils.DATE_FORMAT_5.equals(formatFlag)) {
				dateFormat = "yyyyMMddHHmmssSSS";
			}
			SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
			date = sf.parse(value);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * stringToDecimal(将字符串转为BigDecimal类型) @param value @return
	 * BigDecimal @exception
	 */
	public static BigDecimal stringToDecimal(String value) {
		if (Utils.isEmptyString(value)) {
			return null;
		}
		return new BigDecimal(value.replace(",", ""));
	}

	/**
	 * 日期加上相应的天数
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static String addDays(Date date, Integer days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleFormat.format(calendar.getTime());
	}

	/**
	 * 日期间隔天数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static Integer dateDiff(Date start, Date end) {
		// 得到两个日期的毫秒差
		long diff = end.getTime() - start.getTime();
		int result = (int) (diff / (1000 * 60 * 60 * 24));
		return result;
	}

	/**
	 * 日期间隔毫秒
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static long timeDiff(Date start, Date end) {
		// 得到两个日期的毫秒差
		long diff = end.getTime() - start.getTime();
		return diff;
	}

	/**
	 * 
	 * versionCheck(数据版本校验)
	 * 
	 * @param originalEntity
	 *            页面提交的实体 @param currentEntity 从数据库中查询的实体 @return String
	 *            返回提示信息 @exception
	 */
	public static String versionCheck(Object originalEntity, Object currentEntity) {
		// DB中数据不存在
		if (currentEntity == null) {
			return Constants.DATA_DELETED;
		} else {
			Class<? extends Object> originalClass = originalEntity.getClass();
			Class<? extends Object> currentClass = currentEntity.getClass();
			// 类型不一致
			if (!originalClass.getName().equals(currentClass.getName())) {
				return Constants.NO_VERSION;
			} else {
				try {
					// 获得页面提交的版本号
					Field originalField = originalClass.getSuperclass().getDeclaredField("version");
					Object originalObject = originalField.get(originalEntity);
					// 获得DB中的版本号
					Field currentField = currentClass.getSuperclass().getDeclaredField("version");
					Object currentObject = currentField.get(currentEntity);
					// 两个版本号相等
					if (Utils.timeDiff((Date) originalObject, (Date) currentObject) == 0) {
						return "";
					}
					// 版本已改变
					return Constants.VERSION_CHANGED;
				} catch (Exception e) {
					e.printStackTrace();
					// 无法获得数据版本信息
					return Constants.NO_VERSION;
				}
			}
		}
	}

	/**
	 * 
	 * dataChaged(判断实体类属性是否改变)
	 * 
	 * @param pgEntity
	 *            页面提交的对象 @param dbEntity 数据库中的对象 @return String @exception
	 */
	public static String dataChaged(Object pgEntity, Object dbEntity) {
		return dataChaged(pgEntity, dbEntity, null);
	}

	/**
	 * 
	 * dataChaged(判断实体类属性是否改变)
	 * 
	 * @param pgEntity
	 *            页面提交的对象 @param dbEntity 数据库中的对象 @param value 替换占位符的字符串 @return
	 *            String @exception
	 */
	public static String dataChaged(Object pgEntity, Object dbEntity, String value) {
		// 对象不存在
		if (pgEntity == null || dbEntity == null) {
			return Constants.EMPTY;
		}
		Class<? extends Object> pgClass = pgEntity.getClass();
		Class<? extends Object> dbClass = dbEntity.getClass();
		// 类型不一致
		if (!pgClass.getName().equals(dbClass.getName())) {
			return Constants.EMPTY;
		}
		String result = Constants.EMPTY;
		Resource resource = null;
		Object dbObject = null;
		Field pgField = null;
		Object pgObject = null;
		String resourceName = null;
		try {
			Field[] fields = dbClass.getDeclaredFields();
			for (Field item : fields) {
				resource = item.getAnnotation(Resource.class);
				if (resource != null) {
					// 取消java语言访问检查以访问private变量
					item.setAccessible(true);
					dbObject = item.get(dbEntity);
					pgField = pgClass.getDeclaredField(item.getName());
					pgField.setAccessible(true);
					pgObject = pgField.get(pgEntity);
					if (Utils.isNotEmptyString(value)) {
						resourceName = String.format(resource.name(), value);
					} else {
						resourceName = resource.name();
					}
					if (dbObject == null && pgObject != null) {
						if (Utils.isNotEmptyString(result)) {
							result += ",";
						}
						if (pgObject instanceof Date) {
							SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
							pgObject = simpleFormat.format(pgObject);
						}
						result += resourceName + ":null → " + emptyToNullString(pgObject);
					} else if (dbObject != null && pgObject == null) {
						if (Utils.isNotEmptyString(result)) {
							result += ",";
						}
						if (dbObject instanceof Date) {
							SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
							dbObject = simpleFormat.format(dbObject);
						}
						result += resourceName + ":" + emptyToNullString(dbObject) + " → null";
					} else if (dbObject != null && pgObject != null) {
						if (!dbObject.equals(pgObject)) {
							if (Utils.isNotEmptyString(result)) {
								result += ",";
							}
							if (dbObject instanceof Date) {
								SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
								result += resourceName + ":" + simpleFormat.format(dbObject) + " → "
										+ simpleFormat.format(pgObject);
							} else {
								result += resourceName + ":" + emptyToNullString(dbObject) + " → "
										+ emptyToNullString(pgObject);
							}

						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * emptyToNullString(将空字符串转换为"null")
	 * 
	 * @param value
	 * @return String @exception
	 */
	public static String emptyToNullString(Object value) {
		String result = "null";
		if (Utils.isNotEmptyString(value.toString())) {
			result = value.toString();
		}
		return result;
	}

	/**
	 * 
	 * getStartIndex(计算每页的起始下标)
	 * 
	 * @param pageIndex
	 *            当前页码 @param pageSize 每页 笔数 @param count 数据总笔数 @return int
	 *            当前页起始下标 @exception
	 */
	public static int getStartIndex(int pageIndex, int pageSize, int count) {
		if (pageIndex == 0) {
			return 0;
		}
		// 当前页码开始下标大于记录总数时，重置当前页码，主要用于删除时，数据笔数不足时的页码重置
		else if ((pageIndex - 1) * pageSize + 1 > count) {
			pageIndex = (int) Math.ceil(count / pageSize);
			pageIndex = pageIndex == 0 ? 1 : pageIndex;
		}
		// 计算每页起始下标
		return (pageIndex - 1) * pageSize;
	}
}
