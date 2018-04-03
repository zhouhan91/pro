package com.wemeCity.components.sms.model;

public class SmsBusi {

	/** 业务编码 */
	private String busiCode;

	/** 图片验证码缓存名 */
	private String securityCodeCache;

	/** 发送的短信验证码保存缓存名 */
	private String smsCache;

	public SmsBusi(String busiCode, String securityCodeCache, String smsCache) {
		this.busiCode = busiCode;
		this.securityCodeCache = securityCodeCache;
		this.smsCache = smsCache;
	}

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public String getSecurityCodeCache() {
		return securityCodeCache;
	}

	public void setSecurityCodeCache(String securityCodeCache) {
		this.securityCodeCache = securityCodeCache;
	}

	public String getSmsCache() {
		return smsCache;
	}

	public void setSmsCache(String smsCache) {
		this.smsCache = smsCache;
	}

}