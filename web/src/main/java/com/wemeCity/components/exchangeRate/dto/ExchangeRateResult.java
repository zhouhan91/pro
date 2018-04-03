package com.wemeCity.components.exchangeRate.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ExchangeRateResult {

	/** 更新时间s */
	private LocalDateTime update;

	/** 内容信息 */
	private List<Object[]> list;

	public LocalDateTime getUpdate() {
		return update;
	}

	public void setUpdate(LocalDateTime update) {
		this.update = update;
	}

	public List<Object[]> getList() {
		return list;
	}

	public void setList(List<Object[]> list) {
		this.list = list;
	}

}
