package com.wemeCity.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class StringUtils extends org.springframework.util.StringUtils {

	/**
	 * 生成随机字符串
	 * 
	 * @param length
	 * @return
	 * @Author Xiang XiaoWen 2015年4月2日 创建
	 */
	public static String generateRandomStr(int length) {
		String sourceStr = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(sourceStr.charAt(random.nextInt(sourceStr.length())));
		}
		return sb.toString();
	}
	
	/**
	 * 生成随机字符串
	 * 
	 * @param length
	 * @return
	 * @Author Xiang XiaoWen 2015年4月2日 创建
	 */
	public static String generateLowRandomStr(int length) {
		String sourceStr = "abcdefghijklmnopqrstuvwxyz1234567890";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(sourceStr.charAt(random.nextInt(sourceStr.length())));
		}
		return sb.toString();
	}

	/**
	 * 生成纯数字随机码
	 *
	 * @param length
	 * @return
	 * @Author Xiang xiaowen 2017年6月6日 新建
	 */
	public static String generateNumberRandomStr(int length) {
		String sourceStr = "1234567890";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(sourceStr.charAt(random.nextInt(sourceStr.length())));
		}
		return sb.toString();
	}

	/**
	 * md5
	 * 
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @Author Xiang XiaoWen 2015年4月3日 创建
	 */
	public static String md5(String str) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");  
        md.update(str.getBytes("UTF-8"));  
        byte b[] = md.digest();  
        int i;  
        StringBuffer buf = new StringBuffer();  
        for (int offset = 0; offset < b.length; offset++) {  
            i = b[offset];  
            if (i < 0)  
                i += 256;  
            if (i < 16)  
                buf.append("0");  
            buf.append(Integer.toHexString(i));  
        }  
        //32位加密  
        return buf.toString();
	}

	/**
	 * SHA1加密
	 * 
	 * @param content
	 * @return
	 * @Author Xiang xiaowen 2017年1月12日
	 */
	public static String sha1(String content) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(content.getBytes());
			StringBuffer buf = new StringBuffer();
			byte[] bits = md.digest();
			for (int i = 0; i < bits.length; i++) {
				int a = bits[i];
				if (a < 0)
					a += 256;
				if (a < 16)
					buf.append("0");
				buf.append(Integer.toHexString(a));
			}
			return buf.toString();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 获取当前时间字符串
	 *
	 * @param pattern
	 * @return
	 * @Author Xiang xiaowen 2017年6月15日 新建
	 */
	public static String getLocalTimeStr(String pattern) {
		if (isEmpty(pattern)) {
			pattern = "yyyyMMddHHmmsss";
		}
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
	}
}
