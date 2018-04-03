package com.wemeCity.web.community.dto;

public class CommunityOrderQueryDTO {

	/** 用户标识 */
	private String userKey;

	/** 状态 */
	private String status;

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
