package com.wemeCity.common.exception;

public class BaseException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public BaseException(String message, Exception e){
		super(message, e);
	}

	public BaseException(String message){
		super(message);
	}
}
