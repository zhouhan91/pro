
package com.wemeCity.web.catering.exception;

/**
 * CateringCommentImgException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
public class CateringCommentImgException extends Exception{

	private static final long serialVersionUID = 1L;

	public CateringCommentImgException(String message, Exception e){
		super(message, e);
	}

	public CateringCommentImgException(String message){
		super(message);
	}

}