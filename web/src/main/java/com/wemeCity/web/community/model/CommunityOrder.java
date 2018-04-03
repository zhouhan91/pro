package com.wemeCity.web.community.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CommunityOrder实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-10-8 新建
 */
public class CommunityOrder {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (code,VARCHAR(32), null)订单编码 */
	private String code;

	/** (user_id,BIGINT(20), null)用户 */
	private long userId;

	/** (community_id,BIGINT(20), null) */
	private long communityId;

	/** (community_name,VARCHAR(200), null)公寓名称 */
	private String communityName;

	/** (community_type,VARCHAR(100), null)房屋类型(公寓、民宿等) */
	private String communityType;

	/** (community_img,VARCHAR(500), null)封面图片 */
	private String communityImg;

	/** (city_id,BIGINT(20), null)城市id */
	private long cityId;

	/** (city_name,VARCHAR(256), null)城市 */
	private String cityName;

	/** (district_id,BIGINT(20), null)区县id */
	private long districtId;

	/** (district_name,VARCHAR(256), null)区县名称 */
	private String districtName;

	/** (address,VARCHAR(1000), null)地址 */
	private String address;

	/** (room_id,BIGINT(20), null)房型id */
	private long roomId;

	/** (room_name,VARCHAR(200), null)房型名称 */
	private String roomName;

	/** (room_type,VARCHAR(200), null)房型(一房一厅、多人房等) */
	private String roomType;

	/** (lease_model_key,VARCHAR(50), null) */
	private String leaseModelKey;

	/** (lease_month,INT(11), null)租住月数 */
	private int leaseMonth;

	/** (in_date,DATE, null)入住日期 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate inDate;

	/** (out_date,DATE, null)退房 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate outDate;

	/** (real_name,VARCHAR(200), null)入住人姓名 */
	private String realName;

	/** (phone,VARCHAR(50), null)联系电话 */
	private String phone;

	/** (email,VARCHAR(200), null)邮件 */
	private String email;

	/** (school,VARCHAR(200), null)学校 */
	private String school;

	/** (wechat,VARCHAR(100), null)微信 */
	private String wechat;

	/** (price,DECIMAL(15,4), null)房型价格 */
	private BigDecimal price;

	/** (discount_price,DECIMAL(15,4), null)房型优惠价 */
	private BigDecimal discountPrice;

	/** (first_rent_month,INT(11), null) */
	private int firstRentMonth;

	/** (tip_price,DECIMAL(15,4), null)定金 */
	private BigDecimal tipPrice;

	/** (deposit_price,DECIMAL(15,4), null)押金 */
	private BigDecimal depositPrice;

	/** (first_deposit_month,INT(11), null)首次支付几个月押金 */
	private int firstDepositMonth;

	/** (first_amount,DECIMAL(15,4), null) */
	private BigDecimal firstAmount;

	/** (pay_flag,VARCHAR(1), null)是否需要支付(Y/N) */
	private String payFlag;

	/** (tip_flag,VARCHAR(1), null)是否需要定金(Y/N) */
	private String tipFlag;

	/** (amount,DECIMAL(15,4), null)总金额 */
	private BigDecimal amount;

	/** (coupon,DECIMAL(15,4), null)优惠金额 */
	private BigDecimal coupon;

	/** (real_pay,DECIMAL(15,4), null)实际支付金额 */
	private BigDecimal realPay;

	/** (exchange_rate,DECIMAL(15,4), null)汇率 */
	private BigDecimal exchangeRate;

	/** (real_pay_rmb,DECIMAL(15,4), null)人民币支付金额 */
	private BigDecimal realPayRmb;

	/** (pay_status,VARCHAR(1), null)支付状态(1：新建，2：支付中，3：已支付，4：支付失败) */
	private String payStatus;

	/** (pay_type,VARCHAR(16), null)支付类型(微信：wechat, 支付宝：alipay) */
	private String payType;

	/** (status,VARCHAR(8), null)状态(10:新建，20:支付中，30:支付成功，40：支付失败，200：处理中，300：处理完成，400：取消) */
	private String status;

	/** (order_source,VARCHAR(16), null)订单来源(sdk:移动端，program:微信小程序) */
	private String orderSource;

	/** (confirm_flag,VARCHAR(1), null)是否已确认房(Y/N) */
	private String confirmFlag;

	/** (print_contract_flag,VARCHAR(1), null)是否出合同(Y/N) */
	private String printContractFlag;

	/** (sign_contract_flag,VARCHAR(1), null)是否签合同(Y/N) */
	private String signContractFlag;

	/** (complete_flag,VARCHAR(1), null)是否已完成(Y/N) */
	private String completeFlag;

	/** (is_deleted,VARCHAR(1), null)是否已删除 */
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

	/** 房源对象 */
	private Community community;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setCommunityId(long communityId) {
		this.communityId = communityId;
	}

	public long getCommunityId() {
		return this.communityId;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getCommunityName() {
		return this.communityName;
	}

	public void setCommunityType(String communityType) {
		this.communityType = communityType;
	}

	public String getCommunityType() {
		return this.communityType;
	}

	public void setCommunityImg(String communityImg) {
		this.communityImg = communityImg;
	}

	public String getCommunityImg() {
		return this.communityImg;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public long getCityId() {
		return this.cityId;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}

	public long getDistrictId() {
		return this.districtId;
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

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public long getRoomId() {
		return this.roomId;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return this.roomName;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomType() {
		return this.roomType;
	}

	public void setInDate(LocalDate inDate) {
		this.inDate = inDate;
	}

	public LocalDate getInDate() {
		return this.inDate;
	}

	public void setOutDate(LocalDate outDate) {
		this.outDate = outDate;
	}

	public LocalDate getOutDate() {
		return this.outDate;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
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

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setCoupon(BigDecimal coupon) {
		this.coupon = coupon;
	}

	public BigDecimal getCoupon() {
		return this.coupon;
	}

	public void setRealPay(BigDecimal realPay) {
		this.realPay = realPay;
	}

	public BigDecimal getRealPay() {
		return this.realPay;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayStatus() {
		return this.payStatus;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayType() {
		return this.payType;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public String getOrderSource() {
		return this.orderSource;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
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

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public String getLeaseModelKey() {
		return leaseModelKey;
	}

	public void setLeaseModelKey(String leaseModelKey) {
		this.leaseModelKey = leaseModelKey;
	}

	public int getLeaseMonth() {
		return leaseMonth;
	}

	public void setLeaseMonth(int leaseMonth) {
		this.leaseMonth = leaseMonth;
	}

	public int getFirstRentMonth() {
		return firstRentMonth;
	}

	public void setFirstRentMonth(int firstRentMonth) {
		this.firstRentMonth = firstRentMonth;
	}

	public BigDecimal getTipPrice() {
		return tipPrice;
	}

	public void setTipPrice(BigDecimal tipPrice) {
		this.tipPrice = tipPrice;
	}

	public BigDecimal getDepositPrice() {
		return depositPrice;
	}

	public void setDepositPrice(BigDecimal depositPrice) {
		this.depositPrice = depositPrice;
	}

	public int getFirstDepositMonth() {
		return firstDepositMonth;
	}

	public void setFirstDepositMonth(int firstDepositMonth) {
		this.firstDepositMonth = firstDepositMonth;
	}

	public BigDecimal getFirstAmount() {
		return firstAmount;
	}

	public void setFirstAmount(BigDecimal firstAmount) {
		this.firstAmount = firstAmount;
	}

	public String getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}

	public String getTipFlag() {
		return tipFlag;
	}

	public void setTipFlag(String tipFlag) {
		this.tipFlag = tipFlag;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public String getPrintContractFlag() {
		return printContractFlag;
	}

	public void setPrintContractFlag(String printContractFlag) {
		this.printContractFlag = printContractFlag;
	}

	public String getSignContractFlag() {
		return signContractFlag;
	}

	public void setSignContractFlag(String signContractFlag) {
		this.signContractFlag = signContractFlag;
	}

	public String getCompleteFlag() {
		return completeFlag;
	}

	public void setCompleteFlag(String completeFlag) {
		this.completeFlag = completeFlag;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public BigDecimal getRealPayRmb() {
		return realPayRmb;
	}

	public void setRealPayRmb(BigDecimal realPayRmb) {
		this.realPayRmb = realPayRmb;
	}

}