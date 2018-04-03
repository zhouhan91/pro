package com.wemeCity.common.taglibs;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyTag extends TagSupport {

	private static final long serialVersionUID = 5533800092613650756L;

	private Logger logger = LoggerFactory.getLogger(PropertyTag.class);

	/** 显示的值 */
	private String value;

	/** 截取长度 */
	private int subLenth;

	@Override
	public int doStartTag() throws JspException {
		// 截取
		if (subLenth > 0 && value.length() > subLenth) {
			value = value.substring(0, subLenth)+"...";
		}
		try {
			pageContext.getOut().write(value);
		} catch (Exception e) {
			logger.error("文本标签输出失败！");
		}
		return SKIP_BODY;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getSubLenth() {
		return subLenth;
	}

	public void setSubLenth(int subLenth) {
		this.subLenth = subLenth;
	}

}
