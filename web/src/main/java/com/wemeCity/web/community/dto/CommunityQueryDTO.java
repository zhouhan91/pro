package com.wemeCity.web.community.dto;

public class CommunityQueryDTO {

	/** (city_code,VARCHAR(50), null)城市编码 */
	private String cityCode;

	/** (district_code,VARCHAR(50), null)区县编码 */
	private String districtCode;

	/** (source_type,VARCHAR(100), null)房源类型(中介、个人) */
	private String sourceType;

	/** (type,VARCHAR(100), null)房屋类型(公寓、民宿等) */
	private String type;

	/** (lease_model,VARCHAR(100), null)租赁模式(日/月) */
	private String leaseModel;

	/** (room_type,VARCHAR(100), null)房型(单人卧室、多人卧室、整租套房) */
	private String roomType;

	/** (bathroom_type,VARCHAR(100), null)卫浴类别(独立卫浴、公共卫浴) */
	private String bathroomType;

	/** 关键词 */
	private String keyWords;

	/** 排序字段 */
	private String sortColumn;

	/** 排序方式 */
	private String sortType;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLeaseModel() {
		return leaseModel;
	}

	public void setLeaseModel(String leaseModel) {
		this.leaseModel = leaseModel;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getBathroomType() {
		return bathroomType;
	}

	public void setBathroomType(String bathroomType) {
		this.bathroomType = bathroomType;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

}
