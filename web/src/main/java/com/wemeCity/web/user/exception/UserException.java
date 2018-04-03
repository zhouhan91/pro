
package com.wemeCity.web.user.exception;

/**
 * UserException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-26 新建
 */
public class UserException extends Exception{

	private static final long serialVersionUID = 1L;

	public UserException(String message, Exception e){
		super(message, e);
	}

	public UserException(String message){
		super(message);
	}

}