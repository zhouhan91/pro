package com.wemeCity.common.utils;

import javax.servlet.ServletContext;

public class RequestUtils {

	public static final String getWebRoot(ServletContext servletContext){
		String webRoot=servletContext.getContextPath();
		if(!"/dyj".equals(webRoot)){
			return "";
		}
		return webRoot;
	}
}
