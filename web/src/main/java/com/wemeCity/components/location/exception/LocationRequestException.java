
package com.wemeCity.components.location.exception;

/**
 * LocationRequestException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-17 新建
 */
public class LocationRequestException extends Exception{

	private static final long serialVersionUID = 1L;

	public LocationRequestException(String message, Exception e){
		super(message, e);
	}

	public LocationRequestException(String message){
		super(message);
	}

}