package com.wemeCity.web.catering.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CateringOrder实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-9 新建
 */
public class CateringOrder {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (code,VARCHAR(50), null)订单编码 */
	private String code;

	/** (user_id,BIGINT(20), null)用户id */
	private long userId;

	/** (restaurant_id,BIGINT(20), null)店铺id */
	private long restaurantId;

	/** (price,DECIMAL(15,4), null)价格 */
	private BigDecimal price;

	/** (discount_price,DECIMAL(15,4), null)折扣价 */
	private BigDecimal discountPrice;

	/** (coupon_amount,DECIMAL(15,4), null)使用抵扣券金额 */
	private BigDecimal couponAmount;

	/** (cash_amount,DECIMAL(15,4), null)现金支付金额 */
	private BigDecimal cashAmount;

	/** (exchange_rate,DECIMAL(15,4), null)欧元对人民币汇率 */
	private BigDecimal exchangeRate;

	/** (cash_amount_rmb,DECIMAL(15,4), null)现金支付(人民币) */
	private BigDecimal cashAmountRmb;

	/** (coupon_name,VARCHAR(100), null)抵扣券名称 */
	private String couponName;

	/** (pay_type,VARCHAR(20), null)支付方式(wxpay,alipay) */
	private String payType;

	/** (distribution_location,VARCHAR(200), null)配送位置 */
	private String distributionLocation;

	/** (distribution_amount,DECIMAL(15,4), null)配送费 */
	private BigDecimal distributionAmount;

	/** (city_code,VARCHAR(50), null)配送城市编码 */
	private String cityCode;

	/** (city_name,VARCHAR(200), null)配送城市名称 */
	private String cityName;

	/** (district_code,VARCHAR(50), null)配送区县编码 */
	private String districtCode;

	/** (district_name,VARCHAR(200), null)配送区县名称 */
	private String districtName;

	/** (address,VARCHAR(500), null)配送具体地址 */
	private String address;

	/** (name,VARCHAR(100), null)联系人 */
	private String name;

	/** (phone,VARCHAR(50), null)联系电话 */
	private String phone;

	/** (order_source,VARCHAR(20), null)订单来源(app：app，微信小程序：program) */
	private String orderSource;

	/** (courier_id,BIGINT(20), null)配送员id */
	private long courierId;

	/** (courier_name,VARCHAR(100), null)配送员姓名 */
	private String courierName;

	/** (courier_phone,VARCHAR(50), null)配送员电话 */
	private String courierPhone;

	/** (remark,VARCHAR(500), null)备注 */
	private String remark;

	/** (pay_status,VARCHAR(2), null)支付状态(1：未支付，2：支付中，3：已支付，4：支付失败) */
	private String payStatus;

	/** (status,VARCHAR(2), null)订单状态(10:新建，30:待确认，50：待配送，60：待结算，70：已完成，80：已取消) */
	private String status;

	/** (confirm_time,DATETIME, null)确认时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime confirmTime;

	/** (distribution_time,DATETIME, null)配送时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime distributionTime;

	/** (settlement_time,DATETIME, null)结算时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime settlementTime;

	/** (settlement_amount,DECIMAL(15,4), null)结算金额 */
	private BigDecimal settlementAmount;

	/** (settlement_remark,VARCHAR(500), null)结算备注 */
	private String settlementRemark;

	/** (cancel_reason,VARCHAR(50), null)取消原因 */
	private String cancelReason;

	/** (cancel_remark,VARCHAR(500), null)取消备注 */
	private String cancelRemark;

	/** (comment_flag,VARCHAR(1), null)是否已评论(Y/N) */
	private String commentFlag;

	/** (is_deleted,VARCHAR(1), null)是否已删除(Y/N) */
	private String isDeleted;

	/** (create_by,BIGINT(20), null)创建人 */
	private long createBy;

	/** (create_time,DATETIME, null)创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	/** (modify_by,BIGINT(20), null)最后修改人 */
	private long modifyBy;

	/** (modify_time,DATETIME, null)最后修改时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifyTime;

	/** 订单明细 */
	private List<CateringOrderDetail> lstDetail;

	/** 店铺信息 */
	private CateringRestaurant restaurant;

	/** 配送说明 */
	private String distributionNotes;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public long getRestaurantId() {
		return this.restaurantId;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public BigDecimal getDiscountPrice() {
		return this.discountPrice;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	public BigDecimal getCouponAmount() {
		return this.couponAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	public BigDecimal getCashAmount() {
		return this.cashAmount;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public BigDecimal getCashAmountRmb() {
		return cashAmountRmb;
	}

	public void setCashAmountRmb(BigDecimal cashAmountRmb) {
		this.cashAmountRmb = cashAmountRmb;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponName() {
		return this.couponName;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayType() {
		return this.payType;
	}

	public void setDistributionLocation(String distributionLocation) {
		this.distributionLocation = distributionLocation;
	}

	public String getDistributionLocation() {
		return this.distributionLocation;
	}

	public void setDistributionAmount(BigDecimal distributionAmount) {
		this.distributionAmount = distributionAmount;
	}

	public BigDecimal getDistributionAmount() {
		return this.distributionAmount;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictCode() {
		return this.districtCode;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictName() {
		return this.districtName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public String getOrderSource() {
		return this.orderSource;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayStatus() {
		return this.payStatus;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public LocalDateTime getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(LocalDateTime confirmTime) {
		this.confirmTime = confirmTime;
	}

	public LocalDateTime getDistributionTime() {
		return distributionTime;
	}

	public void setDistributionTime(LocalDateTime distributionTime) {
		this.distributionTime = distributionTime;
	}

	public LocalDateTime getSettlementTime() {
		return settlementTime;
	}

	public void setSettlementTime(LocalDateTime settlementTime) {
		this.settlementTime = settlementTime;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getIsDeleted() {
		return this.isDeleted;
	}

	public void setCreateBy(long createBy) {
		this.createBy = createBy;
	}

	public long getCreateBy() {
		return this.createBy;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getCreateTime() {
		return this.createTime;
	}

	public void setModifyBy(long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public long getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyTime(LocalDateTime modifyTime) {
		this.modifyTime = modifyTime;
	}

	public LocalDateTime getModifyTime() {
		return this.modifyTime;
	}

	public List<CateringOrderDetail> getLstDetail() {
		return lstDetail;
	}

	public void setLstDetail(List<CateringOrderDetail> lstDetail) {
		this.lstDetail = lstDetail;
	}

	public long getCourierId() {
		return courierId;
	}

	public void setCourierId(long courierId) {
		this.courierId = courierId;
	}

	public String getCourierName() {
		return courierName;
	}

	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}

	public String getCourierPhone() {
		return courierPhone;
	}

	public void setCourierPhone(String courierPhone) {
		this.courierPhone = courierPhone;
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

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getCancelRemark() {
		return cancelRemark;
	}

	public void setCancelRemark(String cancelRemark) {
		this.cancelRemark = cancelRemark;
	}

	public String getCommentFlag() {
		return commentFlag;
	}

	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
	}

	public CateringRestaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(CateringRestaurant restaurant) { this.restaurant = restaurant; }

	public String getDistributionNotes() { return distributionNotes; }

	public void setDistributionNotes(String distributionNotes) { this.distributionNotes = distributionNotes; }
}