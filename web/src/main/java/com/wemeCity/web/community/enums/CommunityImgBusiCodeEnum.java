package com.wemeCity.web.community.enums;

public enum CommunityImgBusiCodeEnum {

	/** 公寓 */
	COMMUNITY("community", "公寓"),
	
	/** 房型 */
	ROOM("room", "房型");
	
	private String key;

	private String description;

	private CommunityImgBusiCodeEnum(String key, String description) {
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
