
package com.wemeCity.components.wechatPay.exception;

/**
 * WechatUnifiedOrderException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-8 新建
 */
public class WechatUnifiedOrderException extends Exception{

	private static final long serialVersionUID = 1L;

	public WechatUnifiedOrderException(String message, Exception e){
		super(message, e);
	}

	public WechatUnifiedOrderException(String message){
		super(message);
	}

}