package com.wemeCity.web.user.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * LoginRecord实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-26 新建
 */
public class LoginRecord {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (user_id,BIGINT(20), null) */
	private long userId;

	/** (unionid,VARCHAR(256), null) */
	private String unionid;

	/** (status,VARCHAR(1), null) */
	private String status;

	/** (brand,VARCHAR(256), null)手机品牌 */
	private String brand;

	/** (model,VARCHAR(256), null)手机型号 */
	private String model;

	/** (pixel_ratio,VARCHAR(256), null)设备像素比 */
	private String pixelRatio;

	/** (screen_width,VARCHAR(16), null)屏幕宽度 */
	private String screenWidth;

	/** (screen_height,VARCHAR(16), null)屏幕高度 */
	private String screenHeight;

	/** (window_width,VARCHAR(16), null)可使用窗口宽度 */
	private String windowWidth;

	/** (window_height,VARCHAR(16), null)可使用窗口高度 */
	private String windowHeight;

	/** (language,VARCHAR(16), null)微信设置的语言 */
	private String language;

	/** (version,VARCHAR(16), null)微信版本号 */
	private String version;

	/** (system,VARCHAR(16), null)操作系统版本 */
	private String system;

	/** (platform,VARCHAR(16), null)客户端平台 */
	private String platform;

	/**
	 * (font_size_setting,VARCHAR(16), null)用户字体大小设置。以“我-设置-通用-字体大小”中的设置为准，单位：px
	 */
	private String fontSizeSetting;

	/** (sdk_version,VARCHAR(16), null)客户端基础库版本 */
	private String sdkVersion;

	/** (latitude,FLOAT, null)纬度，浮点数，范围为-90~90，负数表示南纬 */
	private float latitude;

	/** (longitude,FLOAT, null)经度，浮点数，范围为-180~180，负数表示西经 */
	private float longitude;

	/** (speed,FLOAT, null)速度，浮点数，单位m/s */
	private float speed;

	/** (accuracy,VARCHAR(16), null)位置的精确度 */
	private String accuracy;

	/** (altitude,VARCHAR(16), null)高度，单位 m */
	private String altitude;

	/** (verticalAccuracy,VARCHAR(16), null)垂直精度，单位 m（Android 无法获取，返回 0） */
	private String verticalaccuracy;

	/** (horizontalAccuracy,VARCHAR(16), null)水平精度，单位 m */
	private String horizontalaccuracy;

	/** (create_time,DATETIME, null)创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	/** 用户session key */
	private String userKey;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getUnionid() {
		return this.unionid;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getModel() {
		return this.model;
	}

	public void setPixelRatio(String pixelRatio) {
		this.pixelRatio = pixelRatio;
	}

	public String getPixelRatio() {
		return this.pixelRatio;
	}

	public void setScreenWidth(String screenWidth) {
		this.screenWidth = screenWidth;
	}

	public String getScreenWidth() {
		return this.screenWidth;
	}

	public void setScreenHeight(String screenHeight) {
		this.screenHeight = screenHeight;
	}

	public String getScreenHeight() {
		return this.screenHeight;
	}

	public void setWindowWidth(String windowWidth) {
		this.windowWidth = windowWidth;
	}

	public String getWindowWidth() {
		return this.windowWidth;
	}

	public void setWindowHeight(String windowHeight) {
		this.windowHeight = windowHeight;
	}

	public String getWindowHeight() {
		return this.windowHeight;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return this.version;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getSystem() {
		return this.system;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setFontSizeSetting(String fontSizeSetting) {
		this.fontSizeSetting = fontSizeSetting;
	}

	public String getFontSizeSetting() {
		return this.fontSizeSetting;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	public String getSdkVersion() {
		return this.sdkVersion;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLatitude() {
		return this.latitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLongitude() {
		return this.longitude;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getSpeed() {
		return this.speed;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	public String getAccuracy() {
		return this.accuracy;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getAltitude() {
		return this.altitude;
	}

	public void setVerticalaccuracy(String verticalaccuracy) {
		this.verticalaccuracy = verticalaccuracy;
	}

	public String getVerticalaccuracy() {
		return this.verticalaccuracy;
	}

	public void setHorizontalaccuracy(String horizontalaccuracy) {
		this.horizontalaccuracy = horizontalaccuracy;
	}

	public String getHorizontalaccuracy() {
		return this.horizontalaccuracy;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getCreateTime() {
		return this.createTime;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

}