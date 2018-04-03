package com.wemeCity.common.enums;

public enum EHCacheNameEnum {

	/** 注册图片验证码(1小时) */
	REGISTER_CODE("registerCode", "注册图片验证码"),
	/** 注册短信验证码(10分钟) */
	REGISTER_SMS("registerSms", "注册图片验证码"),
	
	/** 找回密码图片验证码(1小时) */
	RETRIEVE_PASSWORD_CODE("retrievePasswordCode","找回密码图片验证码"),
	/** 找回密码短信验证码(1小时) */
	RETRIEVE_PASSWORD_SMS("retrievePasswordSms","找回密码短信验证码"),
	
	/** 实名认证图片验证码(1小时) */
	AUTHENTICATE_CODE("authenticateCode","实名认证图片验证码"),
	/** 实名认证短信验证码(1小时) */
	AUTHENTICATE_SMS("authenticateSms","实名认证短信验证码"),
	
	/** 修改支付密码图片验证码(1小时) */
	PAY_PASSWORD_CODE("payPasswordCode","实名认证图片验证码"),
	/** 修改支付密码短信验证码(1小时) */
	PAY_PASSWORD_SMS("payPasswordSms","实名认证短信验证码"),
	
	;

	private String key;

	private String description;

	private EHCacheNameEnum(String key, String description) {
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
