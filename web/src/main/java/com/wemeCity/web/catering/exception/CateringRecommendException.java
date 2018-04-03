
package com.wemeCity.web.catering.exception;

/**
 * CateringRecommendException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
public class CateringRecommendException extends Exception{

	private static final long serialVersionUID = 1L;

	public CateringRecommendException(String message, Exception e){
		super(message, e);
	}

	public CateringRecommendException(String message){
		super(message);
	}

}