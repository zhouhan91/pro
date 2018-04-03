package com.wemeCity.components.sms.dto;

public class SmsDTO {

	/** 业务编码 */
	private String busiCode;

	/** 手机号码 */
	private String phoneNumber;

	/** 图片验证码 */
	private String imageCode;

	public String getBusiCode() {
		return busiCode;
	}

	public void setBusiCode(String busiCode) {
		this.busiCode = busiCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

}
