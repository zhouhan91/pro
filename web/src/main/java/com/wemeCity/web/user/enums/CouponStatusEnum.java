package com.wemeCity.web.user.enums;

public enum CouponStatusEnum {

	/** 未使用 */
	USABLE("1", "未使用"),

	/** 已使用 */
	USED("2", "已使用");

	private String key;

	private String description;

	private CouponStatusEnum(String key, String description) {
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
