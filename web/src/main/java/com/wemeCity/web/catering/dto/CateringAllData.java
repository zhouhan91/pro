package com.wemeCity.web.catering.dto;

import java.math.BigDecimal;

public class CateringAllData {

	/** 总订单数 */
	private int orderCount;

	/** 完成订单数 */
	private int orderCompleteCount;

	/** 总收入 */
	private BigDecimal income;

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public int getOrderCompleteCount() {
		return orderCompleteCount;
	}

	public void setOrderCompleteCount(int orderCompleteCount) {
		this.orderCompleteCount = orderCompleteCount;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

}
