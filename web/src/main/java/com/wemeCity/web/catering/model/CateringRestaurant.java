package com.wemeCity.web.catering.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CateringRestaurant实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-8 新建
 */
public class CateringRestaurant {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (manager_id,BIGINT(20), null)管理员id */
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
	private double longitude;

	/** (latitude,FLOAT, null)纬度 */
	private double latitude;

	/** (interest_level,FLOAT, null)关注度：5分制 */
	private float interestLevel;

	/** (taste_level,FLOAT, null)口味：10分制 */
	private float tasteLevel;

	/** (environment_level,FLOAT, null)环境：10分制 */
	private float environmentLevel;

	/** (service_level,FLOAT, null)服务：10分制 */
	private float serviceLevel;

	/** (like_count,INT(11), null)推荐人数 */
	private int likeCount;

	/** (key_words,VARCHAR(255), null)关键词，英文逗号分隔 */
	private String keyWords;

	/** (recommend_flag,VARCHAR(255), null)是否推荐(Y/N) */
	private String recommendFlag;

	/** (recommend_sort_num,INT(11), null)推荐排序(升序) */
	private int recommendSortNum;

	/** (distribution_remark,VARCHAR(500), null)配送说明 */
	private String distributionRemark;

	/** (sort_num,INT(11), null)分类排序(升序) */
	private int sortNum;

	/** (status,VARCHAR(1), null)状态(10；未通过，20：已通过，100：已关闭) */
	private String status;

	/** (open_flag,VARCHAR(1), null)开放(Y：开放店铺，N：关闭店铺) 店铺自己控制 */
	private String openFlag;
	
	/** (pause_flag,VARCHAR(1), null)暂停接单(Y：接单，N：暂停接单) 店铺自己控制 */
	private String pauseFlag;

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

	/** 店铺所有者信息 */
	private CateringManager manager;

	/** 折扣信息 */
	private List<CateringDiscount> lstCateringDiscount;

	/** 商品信息 */
	private List<CateringGoods> lstCateringGoods;

	/** 评论信息 */
	private List<CateringComment> lstComment;

	/** 距离 */
	private double distance;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getLocalName() {
		return this.localName;
	}

	public void setParentCategoryId(long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public long getParentCategoryId() {
		return this.parentCategoryId;
	}

	public void setParentCategoryCode(String parentCategoryCode) {
		this.parentCategoryCode = parentCategoryCode;
	}

	public String getParentCategoryCode() {
		return this.parentCategoryCode;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryCode() {
		return this.categoryCode;
	}

	public void setCoverPicture(String coverPicture) {
		this.coverPicture = coverPicture;
	}

	public String getCoverPicture() {
		return this.coverPicture;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryName() {
		return this.countryName;
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

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getNotice() {
		return this.notice;
	}

	public void setServiceTimeCode(String serviceTimeCode) {
		this.serviceTimeCode = serviceTimeCode;
	}

	public String getServiceTimeCode() {
		return this.serviceTimeCode;
	}

	public void setServiceTimeDesc(String serviceTimeDesc) {
		this.serviceTimeDesc = serviceTimeDesc;
	}

	public String getServiceTimeDesc() {
		return this.serviceTimeDesc;
	}

	public void setAmountLimit(BigDecimal amountLimit) {
		this.amountLimit = amountLimit;
	}

	public BigDecimal getAmountLimit() {
		return this.amountLimit;
	}

	public BigDecimal getDistributionAmount() {
		return distributionAmount;
	}

	public void setDistributionAmount(BigDecimal distributionAmount) {
		this.distributionAmount = distributionAmount;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setInterestLevel(float interestLevel) {
		this.interestLevel = interestLevel;
	}

	public float getInterestLevel() {
		return this.interestLevel;
	}

	public void setTasteLevel(float tasteLevel) {
		this.tasteLevel = tasteLevel;
	}

	public float getTasteLevel() {
		return this.tasteLevel;
	}

	public void setEnvironmentLevel(float environmentLevel) {
		this.environmentLevel = environmentLevel;
	}

	public float getEnvironmentLevel() {
		return this.environmentLevel;
	}

	public void setServiceLevel(float serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	public float getServiceLevel() {
		return this.serviceLevel;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getLikeCount() {
		return this.likeCount;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getKeyWords() {
		return this.keyWords;
	}

	public void setRecommendFlag(String recommendFlag) {
		this.recommendFlag = recommendFlag;
	}

	public String getRecommendFlag() {
		return this.recommendFlag;
	}

	public void setRecommendSortNum(int recommendSortNum) {
		this.recommendSortNum = recommendSortNum;
	}

	public int getRecommendSortNum() {
		return this.recommendSortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public int getSortNum() {
		return this.sortNum;
	}

	public String getDistributionRemark() {
		return distributionRemark;
	}

	public void setDistributionRemark(String distributionRemark) {
		this.distributionRemark = distributionRemark;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag;
	}

	public String getOpenFlag() {
		return this.openFlag;
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

	public List<CateringDiscount> getLstCateringDiscount() {
		return lstCateringDiscount;
	}

	public void setLstCateringDiscount(List<CateringDiscount> lstCateringDiscount) {
		this.lstCateringDiscount = lstCateringDiscount;
	}

	public List<CateringGoods> getLstCateringGoods() {
		return lstCateringGoods;
	}

	public void setLstCateringGoods(List<CateringGoods> lstCateringGoods) {
		this.lstCateringGoods = lstCateringGoods;
	}

	public List<CateringComment> getLstComment() {
		return lstComment;
	}

	public void setLstComment(List<CateringComment> lstComment) {
		this.lstComment = lstComment;
	}

	public long getManagerId() {
		return managerId;
	}

	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CateringManager getManager() {
		return manager;
	}

	public void setManager(CateringManager manager) {
		this.manager = manager;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getPauseFlag() {
		return pauseFlag;
	}

	public void setPauseFlag(String pauseFlag) {
		this.pauseFlag = pauseFlag;
	}

}