package com.wemeCity.components.wechatPay.model;

import java.util.Properties;

public class WechatConfig {

	/** 获取sessionKey url */
	private String sessionKeyUrl;

	/** 统一下单url */
	private String unifiedorderUrl;

	/** 小程序appid */
	private String programAppId;

	/** 小程序密钥 */
	private String programSecret;

	/** 移动应用appid */
	private String sdkAppId;

	/** 移动应用密钥 */
	private String sdkSecret;

	/** 商户id */
	private String mchId;

	/** 支付回调地址 */
	private String payNotifyUrl;

	/** 支付商户密钥 */
	private String paySecret;

	/** 属性文件 */
	private Properties properties;

	public void getInstance() {
		this.sessionKeyUrl = properties.getProperty("system.wechat.sessionKeyUrl");
		this.unifiedorderUrl = properties.getProperty("system.wechat.unifiedorderUrl");
		this.programAppId = properties.getProperty("system.wechat.programAppId");
		this.programSecret = properties.getProperty("system.wechat.programSecret");
		this.sdkAppId = properties.getProperty("system.wechat.sdkAppId");
		this.sdkSecret = properties.getProperty("system.wechat.sdkSecret");
		this.mchId = properties.getProperty("system.wechat.mchId");
		this.payNotifyUrl = properties.getProperty("system.wechat.payNotifyUrl");
		this.paySecret = properties.getProperty("system.wechat.paySecret");
	}

	public String getSessionKeyUrl() {
		return sessionKeyUrl;
	}

	public void setSessionKeyUrl(String sessionKeyUrl) {
		this.sessionKeyUrl = sessionKeyUrl;
	}

	public String getUnifiedorderUrl() {
		return unifiedorderUrl;
	}

	public void setUnifiedorderUrl(String unifiedorderUrl) {
		this.unifiedorderUrl = unifiedorderUrl;
	}

	public String getProgramAppId() {
		return programAppId;
	}

	public void setProgramAppId(String programAppId) {
		this.programAppId = programAppId;
	}

	public String getSdkAppId() {
		return sdkAppId;
	}

	public void setSdkAppId(String sdkAppId) {
		this.sdkAppId = sdkAppId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getProgramSecret() {
		return programSecret;
	}

	public void setProgramSecret(String programSecret) {
		this.programSecret = programSecret;
	}

	public String getSdkSecret() {
		return sdkSecret;
	}

	public void setSdkSecret(String sdkSecret) {
		this.sdkSecret = sdkSecret;
	}

	public String getPayNotifyUrl() {
		return payNotifyUrl;
	}

	public void setPayNotifyUrl(String payNotifyUrl) {
		this.payNotifyUrl = payNotifyUrl;
	}

	public String getPaySecret() {
		return paySecret;
	}

	public void setPaySecret(String paySecret) {
		this.paySecret = paySecret;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
