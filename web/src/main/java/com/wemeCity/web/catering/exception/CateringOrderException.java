
package com.wemeCity.web.catering.exception;

/**
 * CateringOrderException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
public class CateringOrderException extends Exception{

	private static final long serialVersionUID = 1L;

	public CateringOrderException(String message, Exception e){
		super(message, e);
	}

	public CateringOrderException(String message){
		super(message);
	}

}