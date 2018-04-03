
package com.wemeCity.web.news.exception;

/**
 * NewsCommentException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-16 新建
 */
public class NewsCommentException extends Exception{

	private static final long serialVersionUID = 1L;

	public NewsCommentException(String message, Exception e){
		super(message, e);
	}

	public NewsCommentException(String message){
		super(message);
	}

}