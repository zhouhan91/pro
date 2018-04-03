package com.wemeCity.common.utils;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BigDecimalUtils {

	private static Logger logger = LoggerFactory.getLogger(BigDecimalUtils.class);

	private BigDecimalUtils() {
	}

	/**
	 * bigdecimal 减法，带非空判断。默认保留2位小数
	 * 
	 * @param source 减数
	 * @param subtrahend 被减数
	 * @return
	 * @Author Xiang xiaowen 2017年4月19日 新建
	 */
	public static BigDecimal subtract(BigDecimal source, BigDecimal subtrahend) {
		return subtract(source, subtrahend, 2);
	}

	/**
	 * Bigdecimal 减法,带非空判断
	 * 
	 * @param source 减数
	 * @param subtrahend 被减数
	 * @param scale 小数位数
	 * @return
	 * @Author Xiang xiaowen 2017年4月21日 新建
	 */
	public static BigDecimal subtract(BigDecimal source, BigDecimal subtrahend, int scale) {
		BigDecimal a = source == null ? BigDecimal.ZERO : source;
		BigDecimal b = subtrahend == null ? BigDecimal.ZERO : subtrahend;
		BigDecimal result = a.subtract(b);
		result = result.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return result;
	}

	/**
	 * BigDecimal 加法， 带非空判断
	 * 
	 * @param source
	 * @param augend
	 * @return
	 * @Author Xiang xiaowen 2017年4月19日 新建
	 */
	public static BigDecimal add(BigDecimal source, BigDecimal augend) {
		BigDecimal a = source == null ? BigDecimal.ZERO : source;
		BigDecimal b = augend == null ? BigDecimal.ZERO : augend;
		return a.add(b);
	}

	/**
	 * BigDecimal 除法，加入非空判断和被除数为0时默认为1
	 * 
	 * @param source 除数
	 * @param divisor 被除数，为空或0时会自动修改为1
	 * @param scale 保留小数位
	 * @param roundingMode 小数位处理方案(舍、入方案，见BigDecimal API)
	 * @return 计算结果
	 * @Author Xiang xiaowen 2017年4月20日 新建
	 */
	public static BigDecimal divide(BigDecimal source, BigDecimal divisor, int scale, int roundingMode) {
		BigDecimal a = source == null ? BigDecimal.ZERO : source;
		BigDecimal b = (divisor == null || BigDecimal.ZERO.equals(divisor)) ? new BigDecimal(1) : divisor;
		return a.divide(b, scale, roundingMode);
	}

	/**
	 * BigDecimal 除法，加入非空判断和被除数为0时默认为1
	 * 
	 * @param source 除数
	 * @param divisor 被除数，为空或0时会自动修改为1
	 * @return 计算结果
	 * @Author Xiang xiaowen 2017年4月20日 新建
	 */
	public static BigDecimal divide(BigDecimal source, BigDecimal divisor) {
		BigDecimal a = source == null ? BigDecimal.ZERO : source;
		BigDecimal b = (divisor == null || BigDecimal.ZERO.equals(divisor)) ? new BigDecimal(1) : divisor;
		return a.divide(b);
	}

	/**
	 * bigdecimal对象比较大小
	 * 
	 * @param source
	 * @param val
	 * @return 参见BigDecimal.compareTo
	 * @Author Xiang xiaowen 2017年4月20日 新建
	 */
	public static int compareTo(BigDecimal source, BigDecimal val) {
		BigDecimal a = source == null ? BigDecimal.ZERO : source;
		BigDecimal b = val == null ? BigDecimal.ZERO : val;
		return a.compareTo(b);
	}

	/**
	 * 乘法
	 *
	 * @param source
	 * @param multiplicand
	 * @return
	 * @Author Xiang xiaowen 2017年6月26日 新建
	 */
	public static BigDecimal multiply(BigDecimal source, BigDecimal multiplicand) {
		BigDecimal a = source == null ? BigDecimal.ZERO : source;
		BigDecimal b = multiplicand == null ? BigDecimal.ZERO : multiplicand;
		return a.multiply(b).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 乘法
	 *
	 * @param source
	 * @param multiplicand
	 * @return
	 * @Author Xiang xiaowen 2017年6月26日 新建
	 */
	public static BigDecimal multiply(BigDecimal source, BigDecimal multiplicand, int scale, int roundingMode) {
		BigDecimal a = source == null ? BigDecimal.ZERO : source;
		BigDecimal b = multiplicand == null ? BigDecimal.ZERO : multiplicand;
		BigDecimal c=a.multiply(b).setScale(2, BigDecimal.ROUND_HALF_UP);
		return c.setScale(scale, roundingMode);
	}

	/**
	 * string to bigdecimal
	 *
	 * @param source
	 * @return
	 * @history 2017年10月8日 新建
	 * @auther xiaowen
	 */
	public static BigDecimal parse(String source) {
		try {
			if (StringUtils.isEmpty(source)) {
				return BigDecimal.ZERO;
			}
			return new BigDecimal(source);
		} catch (Exception e) {
			logger.error("string转换bigdecimal出错, source={}", source);
			return BigDecimal.ZERO;
		}
	}
}
