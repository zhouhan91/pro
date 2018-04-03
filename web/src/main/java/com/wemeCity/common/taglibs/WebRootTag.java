package com.wemeCity.common.taglibs;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wemeCity.common.utils.RequestUtils;

public class WebRootTag extends TagSupport {

	private Logger logger = LoggerFactory.getLogger(WebRootTag.class);

	private static final long serialVersionUID = -5845363828218051618L;

	@Override
	public int doStartTag() throws JspException {
		try {
			 pageContext.getOut().write(RequestUtils.getWebRoot(pageContext.getServletContext()));
		} catch (Exception e) {
			logger.error("webroot tag outwrite error", e);
		}
		return SKIP_BODY;
	}

}
