package com.wemeCity.web.community.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Community实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-19 新建
 */
public class Community implements Serializable {

	private static final long serialVersionUID = -6842759946443229658L;

	/** (id,BIGINT(20),not null)主键 */
	private long id;

	/** (name,VARCHAR(200), null)名字 */
	private String name;

	/** (local_name,VARCHAR(200), null)本地化名字 */
	private String localName;

	/** (country_id,BIGINT(20), null)国家id */
	private long countryId;

	/** (country_code,VARCHAR(50), null)国家code */
	private String countryCode;

	/** (country_name,VARCHAR(200), null)国家名称 */
	private String countryName;

	/** (city_id,BIGINT(20), null)所属城市 */
	private long cityId;

	/** (city_code,VARCHAR(50), null)城市编码 */
	private String cityCode;

	/** (city_name,VARCHAR(200), null)城市名 */
	private String cityName;

	/** (district_id,BIGINT(20), null)区县id */
	private long districtId;

	/** (district_code,VARCHAR(50), null)区县编码 */
	private String districtCode;

	/** (district_name,VARCHAR(200), null)区县名称 */
	private String districtName;

	/** (price,DECIMAL(15,4), null)价格描述 */
	private BigDecimal price;

	/** (discount_price,DECIMAL(15,4), null)折扣价 */
	private BigDecimal discountPrice;

	/** (title,VARCHAR(200), null)标题中文 */
	private String title;

	/** (local_title,VARCHAR(200), null)本地化标题 */
	private String localTitle;

	/** (description,VARCHAR(1000), null)描述 */
	private String description;

	/** (address,VARCHAR(1000), null)地址 */
	private String address;

	/** (file_id,BIGINT(20), null)封面图片文件id */
	private long fileId;

	/** (cover_picture,VARCHAR(500), null)封面图片地址 */
	private String coverPicture;

	/** (room_count,INT(11), null)房源数量 */
	private int roomCount;

	/** (grade,FLOAT(5,1), null)推荐等级 */
	private float grade;

	/** (type_key,VARCHAR(100), null)房屋类型-字典key */
	private String typeKey;

	/** (type,VARCHAR(100), null)房屋类型(公寓、民宿等) */
	private String type;

	/** (lease_type_key,VARCHAR(100), null)租赁方式-字典key */
	private String leaseTypeKey;

	/** (lease_type,VARCHAR(100), null)租赁方式(整租、合租) */
	private String leaseType;

	/** (deposit_type_key,VARCHAR(100), null)押金方式-字典key */
	private String depositTypeKey;

	/** (deposit_type,VARCHAR(100), null)押金方式 */
	private String depositType;

	/** (source_type_key,VARCHAR(100), null, default:)房源类型-字典key */
	private String sourceTypeKey;

	/** (source_type,VARCHAR(100), null)房源类型(中介、个人) */
	private String sourceType;

	/** (lease_model_key,VARCHAR(100), null)租赁模式-字典key */
	private String leaseModelKey;

	/** (lease_model,VARCHAR(100), null)租赁模式(日/月) */
	private String leaseModel;

	/** (bathroom_type_key,VARCHAR(200), null)卫浴类别-字典key */
	private String bathroomTypeKey;

	/** (bathroom_type,VARCHAR(500), null)卫浴类别(独立卫浴、公共卫浴) */
	private String bathroomType;

	/** (owner,VARCHAR(100), null)房东 */
	private String owner;

	/** (owner_phone,VARCHAR(100), null)房东电话 */
	private String ownerPhone;

	/** (owner_wechat,VARCHAR(100), null)房东微信 */
	private String ownerWechat;

	/** (owner_email,VARCHAR(200), null)房东邮箱地址 */
	private String ownerEmail;

	/** (supplier_id,BIGINT(20), null)供应商id */
	private long supplierId;

	/** (supplier_name,VARCHAR(100), null)供应商名字 */
	private String supplierName;

	/** (pay_mode,VARCHAR(100), null)支付保障 */
	private String payMode;

	/** (room_type_key,VARCHAR(200), null)房型-字典key */
	private String roomTypeKey;

	/** (room_type,VARCHAR(500), null)房型(单人卧室、多人卧室、套房整租) */
	private String roomType;

	/** (private_facilities,VARCHAR(500), null)房间设施 */
	private String privateFacilitiesStr;

	/** (comment_facilities,VARCHAR(500), null)公共设施 */
	private String commonFacilitiesStr;

	/** (rent_facilities,VARCHAR(500), null)房租包含 */
	private String rentFacilitiesStr;

	/** (route_description,VARCHAR(500), null)路线描述 */
	private String routeDescription;

	/** (advantage,VARCHAR(500), null)优势说明 */
	private String advantage;

	/** (tip,VARCHAR(500), null)租房须知 */
	private String tip;

	/** (status,VARCHAR(1), null)状态(1：新建，2：已发布，3：已下架) */
	private String status;

	/** (mark_count,INT(11), null)收藏数 */
	private int markCount;

	/** (experience_count,INT(11), null)入住人数 */
	private int experienceCount;

	/** (comment_count,INT(11), null)评论数 */
	private int commentCount;

	/** (longitude,FLOAT, null)经度 */
	private double longitude;

	/** (latitude,FLOAT, null)维度 */
	private double latitude;

	/** (keyWords,VARCHAR(500), null)关键词 */
	private String keyWords;

	/** (video_url,VARCHAR(500), null)视频地址 */
	private String videoUrl;

	/** (sort_num,INT(11), null)排序号 */
	private int sortNum;

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

	/** 图片 */
	private List<CommunityImg> images;

	/** 房型列表 */
	private List<Room> rooms;

	/** 公共设施 */
	private List<Facilities> commonFacilities;

	/** 房间设施 */
	private List<Facilities> privateFacilities;

	/** 房租包含的设施 */
	private List<Facilities> rentFacilities;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getCountryId() {
		return countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
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

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
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

	public long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(long districtId) {
		this.districtId = districtId;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocalTitle() {
		return localTitle;
	}

	public void setLocalTitle(String localTitle) {
		this.localTitle = localTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public String getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(String coverPicture) {
		this.coverPicture = coverPicture;
	}

	public int getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public String getTypeKey() {
		return typeKey;
	}

	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLeaseTypeKey() {
		return leaseTypeKey;
	}

	public void setLeaseTypeKey(String leaseTypeKey) {
		this.leaseTypeKey = leaseTypeKey;
	}

	public String getLeaseType() {
		return leaseType;
	}

	public void setLeaseType(String leaseType) {
		this.leaseType = leaseType;
	}

	public String getDepositTypeKey() {
		return depositTypeKey;
	}

	public void setDepositTypeKey(String depositTypeKey) {
		this.depositTypeKey = depositTypeKey;
	}

	public String getDepositType() {
		return depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public String getSourceTypeKey() {
		return sourceTypeKey;
	}

	public void setSourceTypeKey(String sourceTypeKey) {
		this.sourceTypeKey = sourceTypeKey;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getLeaseModelKey() {
		return leaseModelKey;
	}

	public void setLeaseModelKey(String leaseModelKey) {
		this.leaseModelKey = leaseModelKey;
	}

	public String getLeaseModel() {
		return leaseModel;
	}

	public void setLeaseModel(String leaseModel) {
		this.leaseModel = leaseModel;
	}

	public String getBathroomTypeKey() {
		return bathroomTypeKey;
	}

	public void setBathroomTypeKey(String bathroomTypeKey) {
		this.bathroomTypeKey = bathroomTypeKey;
	}

	public String getBathroomType() {
		return bathroomType;
	}

	public void setBathroomType(String bathroomType) {
		this.bathroomType = bathroomType;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	public String getOwnerWechat() {
		return ownerWechat;
	}

	public void setOwnerWechat(String ownerWechat) {
		this.ownerWechat = ownerWechat;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public String getRoomTypeKey() {
		return roomTypeKey;
	}

	public void setRoomTypeKey(String roomTypeKey) {
		this.roomTypeKey = roomTypeKey;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getPrivateFacilitiesStr() {
		return privateFacilitiesStr;
	}

	public void setPrivateFacilitiesStr(String privateFacilitiesStr) {
		this.privateFacilitiesStr = privateFacilitiesStr;
	}

	public String getCommonFacilitiesStr() {
		return commonFacilitiesStr;
	}

	public void setCommonFacilitiesStr(String commonFacilitiesStr) {
		this.commonFacilitiesStr = commonFacilitiesStr;
	}

	public String getRentFacilitiesStr() {
		return rentFacilitiesStr;
	}

	public void setRentFacilitiesStr(String rentFacilitiesStr) {
		this.rentFacilitiesStr = rentFacilitiesStr;
	}

	public String getRouteDescription() {
		return routeDescription;
	}

	public void setRouteDescription(String routeDescription) {
		this.routeDescription = routeDescription;
	}

	public String getAdvantage() {
		return advantage;
	}

	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getMarkCount() {
		return markCount;
	}

	public void setMarkCount(int markCount) {
		this.markCount = markCount;
	}

	public int getExperienceCount() {
		return experienceCount;
	}

	public void setExperienceCount(int experienceCount) {
		this.experienceCount = experienceCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(long createBy) {
		this.createBy = createBy;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public long getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public LocalDateTime getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(LocalDateTime modifyTime) {
		this.modifyTime = modifyTime;
	}

	public List<CommunityImg> getImages() {
		return images;
	}

	public void setImages(List<CommunityImg> images) {
		this.images = images;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public List<Facilities> getCommonFacilities() {
		return commonFacilities;
	}

	public void setCommonFacilities(List<Facilities> commonFacilities) {
		this.commonFacilities = commonFacilities;
	}

	public List<Facilities> getPrivateFacilities() {
		return privateFacilities;
	}

	public void setPrivateFacilities(List<Facilities> privateFacilities) {
		this.privateFacilities = privateFacilities;
	}

	public List<Facilities> getRentFacilities() {
		return rentFacilities;
	}

	public void setRentFacilities(List<Facilities> rentFacilities) {
		this.rentFacilities = rentFacilities;
	}

}