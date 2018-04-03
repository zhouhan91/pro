package com.wemeCity.components.sms.exception;

import com.wemeCity.common.exception.BaseException;

/**
 * SmsException 模块异常类
 *
 * @author 
 * @since JDK1.8
 * @history 2017-6-6 新建
 */
public class SmsException extends BaseException{

	private static final long serialVersionUID = 1L;

	public SmsException(String message, Exception e){
		super(message, e);
	}

	public SmsException(String message){
		super(message);
	}

}