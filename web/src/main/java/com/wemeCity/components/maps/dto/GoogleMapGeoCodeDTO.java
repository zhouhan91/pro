package com.wemeCity.components.maps.dto;

import java.util.List;

public class GoogleMapGeoCodeDTO {

	/** 状态码 */
	private String status;

	/** 结果信息 */
	private List<GoogleMapGeoCodeResult> results;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<GoogleMapGeoCodeResult> getResults() {
		return results;
	}

	public void setResults(List<GoogleMapGeoCodeResult> results) {
		this.results = results;
	}

}
