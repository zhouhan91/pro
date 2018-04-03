package com.wemeCity.web.community.enums;

public enum FacilitiesBusiCodeEnum {

	/** 公共设施 */
	COMMON("common", "公共设施"),
	
	/** 房间设施 */
	PRIVATE("private", "房间设施"),
	
	/** 房租包含设施*/
	RENT("rent", "房租包含设施");
	
	private String key;

	private String description;

	private FacilitiesBusiCodeEnum(String key, String description) {
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
