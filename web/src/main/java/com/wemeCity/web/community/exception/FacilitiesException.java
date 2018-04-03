
package com.wemeCity.web.community.exception;

/**
 * FacilitiesException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
public class FacilitiesException extends Exception{

	private static final long serialVersionUID = 1L;

	public FacilitiesException(String message, Exception e){
		super(message, e);
	}

	public FacilitiesException(String message){
		super(message);
	}

}