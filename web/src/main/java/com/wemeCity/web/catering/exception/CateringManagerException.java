
package com.wemeCity.web.catering.exception;

/**
 * CateringManagerException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-23 新建
 */
public class CateringManagerException extends Exception{

	private static final long serialVersionUID = 1L;

	public CateringManagerException(String message, Exception e){
		super(message, e);
	}

	public CateringManagerException(String message){
		super(message);
	}

}