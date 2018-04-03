
package com.wemeCity.web.news.exception;

/**
 * NewsMarkException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-16 新建
 */
public class NewsMarkException extends Exception{

	private static final long serialVersionUID = 1L;

	public NewsMarkException(String message, Exception e){
		super(message, e);
	}

	public NewsMarkException(String message){
		super(message);
	}

}