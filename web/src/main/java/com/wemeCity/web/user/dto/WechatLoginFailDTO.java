package com.wemeCity.web.user.dto;

public class WechatLoginFailDTO {

	/** 失败编码 */
	private String errcode;

	/** 失败描述 */
	private String errmsg;

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
