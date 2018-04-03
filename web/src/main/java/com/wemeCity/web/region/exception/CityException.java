
package com.wemeCity.web.region.exception;

/**
 * CityException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
public class CityException extends Exception{

	private static final long serialVersionUID = 1L;

	public CityException(String message, Exception e){
		super(message, e);
	}

	public CityException(String message){
		super(message);
	}

}