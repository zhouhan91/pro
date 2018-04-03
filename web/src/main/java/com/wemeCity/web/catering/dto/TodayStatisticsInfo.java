package com.wemeCity.web.catering.dto;

import java.math.BigDecimal;

public class TodayStatisticsInfo {

	/** 订单总数 */
	private int orderCount;

	/** 完成订单 */
	private int orderCompleteCount;

	/** 待确认订单数 */
	private int orderPaidCount;

	/** 待配送订单数 */
	private int orderConfirmedCount;

	/** 订单总金额 */
	private BigDecimal amount;

	/** 收入 */
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

	public int getOrderPaidCount() {
		return orderPaidCount;
	}

	public void setOrderPaidCount(int orderPaidCount) {
		this.orderPaidCount = orderPaidCount;
	}

	public int getOrderConfirmedCount() {
		return orderConfirmedCount;
	}

	public void setOrderConfirmedCount(int orderConfirmedCount) {
		this.orderConfirmedCount = orderConfirmedCount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

}
