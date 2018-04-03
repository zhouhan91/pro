package com.wemeCity.common.taglibs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTag extends TagSupport {

	private static final long serialVersionUID = 82187839436645120L;

	private Logger logger = LoggerFactory.getLogger(DateTag.class);

	private Object value;

	private String pattern;

	@Override
	public int doStartTag() throws JspException {
		String outString = "";
		if (value instanceof LocalDateTime) {
			LocalDateTime time = (LocalDateTime) value;
			outString = time.format(DateTimeFormatter.ofPattern(pattern));
		} else if (value instanceof LocalDate) {
			LocalDate time = (LocalDate) value;
			outString = time.format(DateTimeFormatter.ofPattern(pattern));
		}
		try {
			pageContext.getOut().write(outString);
		} catch (Exception e) {
			logger.error("文本标签输出失败！");
		}
		return SKIP_BODY;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
