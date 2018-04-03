package com.wemeCity.web.catering.dto;

import java.math.BigDecimal;

public class CateringOrderSettlingDTO {

	/** 当前登录用户id */
	private long managerId;

	/** 订单id */
	private long orderId;

	/** 结算金额 */
	private BigDecimal settlementAmount;

	/** 结算备注 */
	private String settlementRemark;

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

	public BigDecimal getSettlementAmount() {
		return settlementAmount;
	}

	public void setSettlementAmount(BigDecimal settlementAmount) {
		this.settlementAmount = settlementAmount;
	}

	public String getSettlementRemark() {
		return settlementRemark;
	}

	public void setSettlementRemark(String settlementRemark) {
		this.settlementRemark = settlementRemark;
	}

}
