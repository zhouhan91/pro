package com.wemeCity.web.catering.dto;

import java.math.BigDecimal;
import java.util.List;

import com.wemeCity.web.catering.model.CateringRestaurantLocation;

public class RestaurantLocationSaveDTO {

	/** 店铺管理员id */
	private long managerId;

	/** 店铺id */
	private long restaurantId;

	/** (amount_limit,DECIMAL(15,4), null, default:0.0000)起送金额 */
	private BigDecimal amountLimit;

	/** (distribution_amount,DECIMAL(15,4), null)配送费 */
	private BigDecimal distributionAmount;

	/** 配送地址信息 */
	private List<CateringRestaurantLocation> lstLocation;

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

	public BigDecimal getAmountLimit() {
		return amountLimit;
	}

	public void setAmountLimit(BigDecimal amountLimit) {
		this.amountLimit = amountLimit;
	}

	public BigDecimal getDistributionAmount() {
		return distributionAmount;
	}

	public void setDistributionAmount(BigDecimal distributionAmount) {
		this.distributionAmount = distributionAmount;
	}

	public List<CateringRestaurantLocation> getLstLocation() {
		return lstLocation;
	}

	public void setLstLocation(List<CateringRestaurantLocation> lstLocation) {
		this.lstLocation = lstLocation;
	}

}
