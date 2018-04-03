
package com.wemeCity.web.news.exception;

/**
 * NewsCommentLikeException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-19 新建
 */
public class NewsCommentLikeException extends Exception{

	private static final long serialVersionUID = 1L;

	public NewsCommentLikeException(String message, Exception e){
		super(message, e);
	}

	public NewsCommentLikeException(String message){
		super(message);
	}

}