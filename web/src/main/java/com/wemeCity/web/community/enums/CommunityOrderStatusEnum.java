package com.wemeCity.web.community.enums;

public enum CommunityOrderStatusEnum {

	/** 新建 */
	NEW("10", "新建"),
	
	/** 支付中 */
	PAYING("20", "支付中"),
	
	/** 已支付 */
	PAID("30", "已支付"),
	
	/** 支付失败 */
	PAY_FAIL("40", "支付失败"),
	
	/** 已取消 */
	CANCELED("50", "已取消"),
	
	/** 已退款 */
	REFUND("60", "已退款"),
	
	
	
	;
	
	private String key;

	private String description;

	private CommunityOrderStatusEnum(String key, String description) {
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
