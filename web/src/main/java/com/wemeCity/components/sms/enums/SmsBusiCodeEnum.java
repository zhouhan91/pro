package com.wemeCity.components.sms.enums;

public enum SmsBusiCodeEnum {
	
	/** 注册 */
	REGISTER("register","注册"),
	/** 找回密码 */
	RETRIEVE_PASSWORD("retrievePassword","找回密码"),
	/** 实名认证 */
	AUTHENTICATE("authenticate", "实名认证"),
	/** 修改支付密码 */
	PAY_PASSWORD("payPassword", "修改支付密码"),
	;
	
	private String key;

	private String description;

	private SmsBusiCodeEnum(String key, String description) {
		this.key = key;
		this.description = description;
	}

	public String getKey() {
		return key;
	}

	public String getDescription() {
		return description;
	}
}
