package com.wemeCity.web.catering.dto;


public class CateringOrderCancelDTO {

	/** 当前登录用户id */
	private long managerId;

	/** 订单id */
	private long orderId;

	/** 取消原因 */
	private String cancelReason;

	/** 取消备注 */
	private String cancelRemark;

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

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getCancelRemark() {
		return cancelRemark;
	}

	public void setCancelRemark(String cancelRemark) {
		this.cancelRemark = cancelRemark;
	}

}
