
package com.wemeCity.web.user.exception;

/**
 * UserSessionException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-26 新建
 */
public class UserSessionException extends Exception{

	private static final long serialVersionUID = 1L;

	public UserSessionException(String message, Exception e){
		super(message, e);
	}

	public UserSessionException(String message){
		super(message);
	}

}