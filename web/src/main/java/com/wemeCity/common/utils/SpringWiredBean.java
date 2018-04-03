package com.wemeCity.common.utils;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * spring bean工具类
 * 
 * @author Xiang xiaowen
 * @since JDK1.8
 * @history 2017年1月17日 新建
 */
public class SpringWiredBean extends SpringBeanAutowiringSupport {

	@Autowired
	private BeanFactory beanFactory;

	/** 单例对象 */
	private static SpringWiredBean instance = new SpringWiredBean();

	public static SpringWiredBean getInstance() {
		return instance;
	}

	public Object getBeanById(String beanId) {
		return beanFactory.getBean(beanId);
	}
}
