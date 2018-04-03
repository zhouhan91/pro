package com.wemeCity.web.community.enums;

public enum CommunityStatusEnum {

	/** 新建 */
	NEW("10", "新建"),
	
	/** 已上架 */
	ON_LINE("20", "已上架"),
	
	/** 已下架 */
	OFF_LINE("30", "已下架");
	
	private String key;

	private String description;

	private CommunityStatusEnum(String key, String description) {
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
