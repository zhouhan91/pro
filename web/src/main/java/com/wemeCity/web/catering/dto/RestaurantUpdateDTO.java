package com.wemeCity.web.catering.dto;

import java.math.BigDecimal;

public class RestaurantUpdateDTO {

	/** id */
	private long id;

	/** managerId */
	private long managerId;

	/** (name,VARCHAR(500), null)店铺名字 */
	private String name;

	/** (local_name,VARCHAR(500), null)店铺本地名字 */
	private String localName;

	/** (parent_category_id,BIGINT(20), null)父分类id */
	private long parentCategoryId;

	/** (parent_category_code,VARCHAR(50), null)父分类编码 */
	private String parentCategoryCode;

	/** (category_id,BIGINT(20), null)分类id */
	private long categoryId;

	/** (category_code,VARCHAR(50), null)分类编码 */
	private String categoryCode;

	/** (cover_picture,VARCHAR(500), null)封面图片 */
	private String coverPicture;

	/** (country_code,VARCHAR(50), null)国家编码 */
	private String countryCode;

	/** (country_name,VARCHAR(200), null)国家名称 */
	private String countryName;

	/** (city_code,VARCHAR(50), null)城市编码 */
	private String cityCode;

	/** (city_name,VARCHAR(200), null)城市名称 */
	private String cityName;

	/** (district_code,VARCHAR(50), null)区县编码 */
	private String districtCode;

	/** (district_name,VARCHAR(200), null)区县名称 */
	private String districtName;

	/** (address,VARCHAR(500), null)地址 */
	private String address;

	/** (manager_phone,VARCHAR(50), null)店主电话 */
	private String managerPhone;

	/** (phone,VARCHAR(50), null)电话 */
	private String phone;

	/** (email,VARCHAR(200), null)联系email */
	private String email;

	/** (notice,VARCHAR(200), null)公告 */
	private String notice;

	/** (service_time_code,VARCHAR(200), null)服务时间编码 */
	private String serviceTimeCode;

	/** (service_time_desc,VARCHAR(200), null)服务时间 */
	private String serviceTimeDesc;

	/** (amount_limit,DECIMAL(15,4), null, default:0.0000)起送金额 */
	private BigDecimal amountLimit;

	/** (distribution_amount,DECIMAL(15,4), null)配送费 */
	private BigDecimal distributionAmount;

	/** (longitude,FLOAT, null)经度 */
	private Double longitude;

	/** (latitude,FLOAT, null)纬度 */
	private Double latitude;

	/** (open_flag,VARCHAR(1), null)开放(Y：开放店铺，N：关闭店铺) 店铺自己控制 */
	private String openFlag;

	/** (pause_flag,VARCHAR(1), null)暂停接单(Y：接单，N：暂停接单) 店铺自己控制 */
	private String pauseFlag;
	
	/** (distribution_remark,VARCHAR(500), null)配送说明 */
	private String distributionRemark;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getManagerId() {
		return managerId;
	}

	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public long getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public String getParentCategoryCode() {
		return parentCategoryCode;
	}

	public void setParentCategoryCode(String parentCategoryCode) {
		this.parentCategoryCode = parentCategoryCode;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(String coverPicture) {
		this.coverPicture = coverPicture;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
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

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getServiceTimeCode() {
		return serviceTimeCode;
	}

	public void setServiceTimeCode(String serviceTimeCode) {
		this.serviceTimeCode = serviceTimeCode;
	}

	public String getServiceTimeDesc() {
		return serviceTimeDesc;
	}

	public void setServiceTimeDesc(String serviceTimeDesc) {
		this.serviceTimeDesc = serviceTimeDesc;
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

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getOpenFlag() {
		return openFlag;
	}

	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag;
	}

	public String getDistributionRemark() {
		return distributionRemark;
	}

	public void setDistributionRemark(String distributionRemark) {
		this.distributionRemark = distributionRemark;
	}

	public String getPauseFlag() {
		return pauseFlag;
	}

	public void setPauseFlag(String pauseFlag) {
		this.pauseFlag = pauseFlag;
	}

}
