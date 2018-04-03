
package com.wemeCity.web.news.exception;

/**
 * NewsBabietaException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-20 新建
 */
public class NewsBabietaException extends Exception{

	private static final long serialVersionUID = 1L;

	public NewsBabietaException(String message, Exception e){
		super(message, e);
	}

	public NewsBabietaException(String message){
		super(message);
	}

}