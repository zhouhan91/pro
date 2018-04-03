
package com.wemeCity.web.catering.exception;

/**
 * CateringContactsException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-8 新建
 */
public class CateringContactsException extends Exception{

	private static final long serialVersionUID = 1L;

	public CateringContactsException(String message, Exception e){
		super(message, e);
	}

	public CateringContactsException(String message){
		super(message);
	}

}