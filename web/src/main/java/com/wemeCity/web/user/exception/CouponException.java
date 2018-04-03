
package com.wemeCity.web.user.exception;

/**
 * CouponException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-28 新建
 */
public class CouponException extends Exception{

	private static final long serialVersionUID = 1L;

	public CouponException(String message, Exception e){
		super(message, e);
	}

	public CouponException(String message){
		super(message);
	}

}