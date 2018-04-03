
package com.wemeCity.web.catering.exception;

/**
 * CateringDiscountException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
public class CateringDiscountException extends Exception{

	private static final long serialVersionUID = 1L;

	public CateringDiscountException(String message, Exception e){
		super(message, e);
	}

	public CateringDiscountException(String message){
		super(message);
	}

}