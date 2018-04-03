package com.wemeCity.web.catering.dto;

public class CateringOrderQueryDTO {

	/** 店铺id */
	private long restaurantId;

	/** 订单状态 */
	private String status;

	/** 配送员id */
	private long courierId;

	/** 页码 */
	private int pageNum;

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCourierId() {
		return courierId;
	}

	public void setCourierId(long courierId) {
		this.courierId = courierId;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

}
