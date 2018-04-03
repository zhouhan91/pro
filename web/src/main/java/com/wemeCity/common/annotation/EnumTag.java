package com.wemeCity.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 枚举显示自定义标签
 * 
 * @author Xiang xiaowen
 * @since JDK8
 * @history 2017年6月18日 新建
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnumTag {

	String value() default "";
}
