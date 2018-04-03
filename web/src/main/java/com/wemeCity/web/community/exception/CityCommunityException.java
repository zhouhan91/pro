
package com.wemeCity.web.community.exception;

/**
 * CityCommunityException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-13 新建
 */
public class CityCommunityException extends Exception{

	private static final long serialVersionUID = 1L;

	public CityCommunityException(String message, Exception e){
		super(message, e);
	}

	public CityCommunityException(String message){
		super(message);
	}

}