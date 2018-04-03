
package com.wemeCity.web.region.exception;

/**
 * DistrictException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-18 新建
 */
public class DistrictException extends Exception{

	private static final long serialVersionUID = 1L;

	public DistrictException(String message, Exception e){
		super(message, e);
	}

	public DistrictException(String message){
		super(message);
	}

}