package com.wemeCity.common.utils;

import java.math.BigDecimal;
import java.util.Map;

public final class ConditionUtils {

	/**
	 * 添加字符串
	 *
	 * @param condition
	 * @param key
	 * @param value
	 * @Author Xiang xiaowen 2017年6月11日 新建
	 */
	public static void addStr(Map<String, Object> condition, String key, String value) {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
			return;
		}
		condition.put(key, value);
	}

	/**
	 * 添加字符串数组
	 *
	 * @param condition
	 * @param key
	 * @param value
	 * @Author Xiang xiaowen 2017年9月19日 新建
	 */
	public static void addStrArray(Map<String, Object> condition, String key, String value) {
		if (!StringUtils.isEmpty(value)) {
			condition.put(key, value.split(","));
		}
	}

	/**
	 * 加integer
	 *
	 * @param condition
	 * @param key
	 * @param value
	 * @Author Xiang xiaowen 2017年6月21日 新建
	 */
	public static void addInteger(Map<String, Object> condition, String key, Integer value) {
		if (StringUtils.isEmpty(key) || value <= 0) {
			return;
		}
		condition.put(key, value);
	}

	/**
	 * 加long
	 *
	 * @param condition
	 * @param key
	 * @param value
	 * @Author Xiang xiaowen 2017年6月21日 新建
	 */
	public static void addLong(Map<String, Object> condition, String key, Long value) {
		if (StringUtils.isEmpty(key) || value <= 0) {
			return;
		}
		condition.put(key, value);
	}
	
	/**
	 * 加Float
	 *
	 * @param condition
	 * @param key
	 * @param value
	 * @Author Xiang xiaowen 2017年6月21日 新建
	 */
	public static void addFloat(Map<String, Object> condition, String key, Float value) {
		if (StringUtils.isEmpty(key) || value == null) {
			return;
		}
		condition.put(key, value);
	}

	/**
	 * 加BigDecimal
	 *
	 * @param condition
	 * @param key
	 * @param value
	 * @Author Xiang xiaowen 2017年6月21日 新建
	 */
	public static void addDecimal(Map<String, Object> condition, String key, BigDecimal value) {
		if (StringUtils.isEmpty(key) || value != null && BigDecimalUtils.compareTo(value, BigDecimal.ZERO) <= 0) {
			return;
		}
		condition.put(key, value);
	}

	/**
	 * 加object
	 *
	 * @param condition
	 * @param key
	 * @param value
	 * @Author Xiang xiaowen 2017年6月21日 新建
	 */
	public static void addObject(Map<String, Object> condition, String key, Object value) {
		if (StringUtils.isEmpty(key) || value == null) {
			return;
		}
		condition.put(key, value);
	}
}
