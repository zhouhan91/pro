package com.wemeCity.components.wechatPay.dto;

import java.math.BigDecimal;

public class PayDTO {

	/** 订单编码 */
	private String orderCode;

	/** 订单金额 */
	private BigDecimal amount;

	/** 订单来源 */
	private String orderSource;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

}
