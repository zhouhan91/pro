package com.wemeCity.web.community.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommunityOrderDTO {

	/** 用户标识 */
	private String userKey;

	/** (community_id,BIGINT(20), null) */
	private long communityId;

	/** (room_id,BIGINT(20), null)房型id */
	private long roomId;

	/** (in_date,DATE, null)入住日期 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate inDate;

	/** (out_date,DATE, null)退房 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate outDate;

	/** 租住月份 */
	private int leaseMonth;

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

	/** (coupon_id,BIGINT(20),not null)优惠券id */
	private long couponId;

	/** 支付类型：wechat,alipay */
	private String payType;

	/** (order_source,VARCHAR(16), null)订单来源(sdk:移动端，program:微信小程序) */
	private String orderSource;

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public long getCommunityId() {
		return communityId;
	}

	public void setCommunityId(long communityId) {
		this.communityId = communityId;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public LocalDate getInDate() {
		return inDate;
	}

	public void setInDate(LocalDate inDate) {
		this.inDate = inDate;
	}

	public LocalDate getOutDate() {
		return outDate;
	}

	public void setOutDate(LocalDate outDate) {
		this.outDate = outDate;
	}

	public int getLeaseMonth() {
		return leaseMonth;
	}

	public void setLeaseMonth(int leaseMonth) {
		this.leaseMonth = leaseMonth;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

}
