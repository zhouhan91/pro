
package com.wemeCity.web.community.exception;

/**
 * CommunityException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
public class CommunityException extends Exception{

	private static final long serialVersionUID = 1L;

	public CommunityException(String message, Exception e){
		super(message, e);
	}

	public CommunityException(String message){
		super(message);
	}

}