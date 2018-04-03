package com.wemeCity.web.catering.dto;

public class RestaurantQueryDTO {

	/** 城市名 */
	private String cityName;

	/** 区县名 */
	private String districtName;

	/** 分类名 */
	private String parentCategoryCode;

	/** 子分类名 */
	private String categoryCode;

	/** 是否获取到位置 */
	private String locationFlag;

	/** (longitude,FLOAT, null)经度 */
	private float longitude;

	/** (latitude,FLOAT, null)纬度 */
	private float latitude;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getParentCategoryCode() {
		return parentCategoryCode;
	}

	public void setParentCategoryCode(String parentCategoryCode) {
		this.parentCategoryCode = parentCategoryCode;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getLocationFlag() {
		return locationFlag;
	}

	public void setLocationFlag(String locationFlag) {
		this.locationFlag = locationFlag;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

}
