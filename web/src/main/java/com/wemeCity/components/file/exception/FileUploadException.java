package com.wemeCity.components.file.exception;

/**
 * FileUploadException 模块异常类
 *
 * @author 
 * @since JDK1.8
 * @history 2017-6-19 新建
 */
public class FileUploadException extends Exception{

	private static final long serialVersionUID = 1L;

	public FileUploadException(String message, Exception e){
		super(message, e);
	}

	public FileUploadException(String message){
		super(message);
	}

}