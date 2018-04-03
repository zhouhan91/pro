
package com.wemeCity.web.news.exception;

/**
 * NewsSubNavigationException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-19 新建
 */
public class NewsSubNavigationException extends Exception{

	private static final long serialVersionUID = 1L;

	public NewsSubNavigationException(String message, Exception e){
		super(message, e);
	}

	public NewsSubNavigationException(String message){
		super(message);
	}

}