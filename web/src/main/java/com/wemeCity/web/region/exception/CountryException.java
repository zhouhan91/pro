
package com.wemeCity.web.region.exception;

/**
 * CountryException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
public class CountryException extends Exception{

	private static final long serialVersionUID = 1L;

	public CountryException(String message, Exception e){
		super(message, e);
	}

	public CountryException(String message){
		super(message);
	}

}