package com.wemeCity.web.catering.dto;

public class CateringOrderDistributeDTO {

	/** 当前登录用户id */
	private long managerId;

	/** 订单id */
	private long orderId;

	/** 配送员id */
	private long courierId;

	public long getManagerId() {
		return managerId;
	}

	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getCourierId() {
		return courierId;
	}

	public void setCourierId(long courierId) {
		this.courierId = courierId;
	}

}
