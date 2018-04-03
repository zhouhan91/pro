
package com.wemeCity.web.catering.exception;

/**
 * CateringCourierException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-25 新建
 */
public class CateringCourierException extends Exception{

	private static final long serialVersionUID = 1L;

	public CateringCourierException(String message, Exception e){
		super(message, e);
	}

	public CateringCourierException(String message){
		super(message);
	}

}