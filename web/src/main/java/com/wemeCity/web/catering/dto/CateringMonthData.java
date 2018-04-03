package com.wemeCity.web.catering.dto;

import java.math.BigDecimal;

public class CateringMonthData {

	/** 订单数 */
	private int orderCount;

	/** 完成订单数 */
	private int orderCompleteCount;

	/** 取消的订单数 */
	private int orderCancelCount;

	/** 总收入 */
	private BigDecimal income;

	/** 线下支付 */
	private BigDecimal incomeOffLine;

	/** 线上支付 */
	private BigDecimal incomeOnLine;

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

	public int getOrderCancelCount() {
		return orderCancelCount;
	}

	public void setOrderCancelCount(int orderCancelCount) {
		this.orderCancelCount = orderCancelCount;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public BigDecimal getIncomeOffLine() {
		return incomeOffLine;
	}

	public void setIncomeOffLine(BigDecimal incomeOffLine) {
		this.incomeOffLine = incomeOffLine;
	}

	public BigDecimal getIncomeOnLine() {
		return incomeOnLine;
	}

	public void setIncomeOnLine(BigDecimal incomeOnLine) {
		this.incomeOnLine = incomeOnLine;
	}

}
