package com.wemeCity.components.location.dto;

public class LocationRequestDTO {

	/** required=true (latitude,FLOAT, null) (必填) 纬度，浮点数，范围为-90~90，负数表示南纬 */
	private float latitude;

	/**required=true (longitude,FLOAT, null) (必填) 经度，浮点数，范围为-180~180，负数表示西经 */
	private float longitude;

	/** (speed,FLOAT, null)速度，浮点数，单位m/s */
	private float speed;

	/** (accuracy,FLOAT, null)位置的精确度 */
	private float accuracy;

	/** (altitude,FLOAT, null)高度，单位 m */
	private float altitude;

	/** (vertical_accuracy,FLOAT, null)垂直精度，单位 m（Android 无法获取，返回 0） */
	private float verticalAccuracy;

	/** (horizontal_accuracy,FLOAT, null)水平精度，单位 m */
	private float horizontalAccuracy;

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}

	public float getAltitude() {
		return altitude;
	}

	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}

	public float getVerticalAccuracy() {
		return verticalAccuracy;
	}

	public void setVerticalAccuracy(float verticalAccuracy) {
		this.verticalAccuracy = verticalAccuracy;
	}

	public float getHorizontalAccuracy() {
		return horizontalAccuracy;
	}

	public void setHorizontalAccuracy(float horizontalAccuracy) {
		this.horizontalAccuracy = horizontalAccuracy;
	}
	
	
}
