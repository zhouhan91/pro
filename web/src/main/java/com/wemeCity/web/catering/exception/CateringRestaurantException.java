
package com.wemeCity.web.catering.exception;

/**
 * CateringRestaurantException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
public class CateringRestaurantException extends Exception{

	private static final long serialVersionUID = 1L;

	public CateringRestaurantException(String message, Exception e){
		super(message, e);
	}

	public CateringRestaurantException(String message){
		super(message);
	}

}