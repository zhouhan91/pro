package com.wemeCity.web.catering.dto;

import java.time.LocalDateTime;

public class CateringPayDTO {

	/** 联系人 */
	private long contactsId;

	/** 配送点id */
	private long locationId;

	/** 店铺id */
	private long restaurantId;

	/** 用户key */
	private String userKey;

	/** 支付方式 */
	private String payType;

	/** 商品 */
	private String goodsIdStr;

	/** 数量 */
	private String countStr;

	/** 备注 */
	private String remark;

	/** 订单来源：移动端app：app，微信小程序：program */
	private String orderSource;

	/** 配送说明 */
	private String distributionNotes;

	/** 配送说明 */
	private LocalDateTime createTime;


	public long getContactsId() {
		return contactsId;
	}

	public void setContactsId(long contactsId) {
		this.contactsId = contactsId;
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getGoodsIdStr() {
		return goodsIdStr;
	}

	public void setGoodsIdStr(String goodsIdStr) {
		this.goodsIdStr = goodsIdStr;
	}

	public String getCountStr() {
		return countStr;
	}

	public void setCountStr(String countStr) {
		this.countStr = countStr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public String getDistributionNotes() {
		return distributionNotes;
	}

	public void setDistributionNotes(String distributionNotes) {
		this.distributionNotes = distributionNotes;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
}
