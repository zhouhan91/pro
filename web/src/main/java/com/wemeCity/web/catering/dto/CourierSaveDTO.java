package com.wemeCity.web.catering.dto;

import java.util.List;

import com.wemeCity.web.catering.model.CateringCourier;

public class CourierSaveDTO {

	/** 管理员id */
	private long managerId;

	/** 店铺id */
	private long restaurantId;

	/** 配送员信息 */
	private List<CateringCourier> lstCourier;

	public long getManagerId() {
		return managerId;
	}

	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public List<CateringCourier> getLstCourier() {
		return lstCourier;
	}

	public void setLstCourier(List<CateringCourier> lstCourier) {
		this.lstCourier = lstCourier;
	}

}
