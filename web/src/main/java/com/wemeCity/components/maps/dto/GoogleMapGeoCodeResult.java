package com.wemeCity.components.maps.dto;

import java.util.List;

public class GoogleMapGeoCodeResult {

	/** 地址组件 */
	private List<GoogleMapGeoCodeAddressComponents> address_components;

	/** 地址信息 */
	private Object geometry;

	/** 结构化地址 */
	private String formatted_address;

	/** 地址id */
	private String place_id;

	/** 类型 */
	private List<String> types;

	public List<GoogleMapGeoCodeAddressComponents> getAddress_components() {
		return address_components;
	}

	public void setAddress_components(List<GoogleMapGeoCodeAddressComponents> address_components) {
		this.address_components = address_components;
	}

	public Object getGeometry() {
		return geometry;
	}

	public void setGeometry(Object geometry) {
		this.geometry = geometry;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public String getPlace_id() {
		return place_id;
	}

	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

}
