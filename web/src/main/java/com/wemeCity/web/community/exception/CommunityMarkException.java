
package com.wemeCity.web.community.exception;

/**
 * CommunityMarkException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-28 新建
 */
public class CommunityMarkException extends Exception{

	private static final long serialVersionUID = 1L;

	public CommunityMarkException(String message, Exception e){
		super(message, e);
	}

	public CommunityMarkException(String message){
		super(message);
	}

}