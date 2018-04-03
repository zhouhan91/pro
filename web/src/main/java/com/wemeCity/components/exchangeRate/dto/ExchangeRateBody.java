package com.wemeCity.components.exchangeRate.dto;

public class ExchangeRateBody {

	/** 错误编码 */
	private String error_code;

	/** 错误原因 */
	private String reason;

	/** 请求内容 */
	private ExchangeRateResult result;

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public ExchangeRateResult getResult() {
		return result;
	}

	public void setResult(ExchangeRateResult result) {
		this.result = result;
	}
}
