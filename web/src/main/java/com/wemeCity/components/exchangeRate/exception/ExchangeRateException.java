
package com.wemeCity.components.exchangeRate.exception;

/**
 * ExchangeRateException 模块异常类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-16 新建
 */
public class ExchangeRateException extends Exception{

	private static final long serialVersionUID = 1L;

	public ExchangeRateException(String message, Exception e){
		super(message, e);
	}

	public ExchangeRateException(String message){
		super(message);
	}

}