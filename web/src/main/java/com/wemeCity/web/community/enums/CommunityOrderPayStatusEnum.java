package com.wemeCity.web.community.enums;

public enum CommunityOrderPayStatusEnum {

	/** 新建 */
	NEW("1", "新建"),
	
	/** 支付中 */
	PAYING("2", "支付中"),
	
	/** 已支付 */
	PAID("3", "已支付"),
	
	/** 支付失败 */
	PAY_FAIL("4", "支付失败"),
	
	
	;
	
	private String key;

	private String description;

	private CommunityOrderPayStatusEnum(String key, String description) {
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
