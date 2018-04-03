
package com.wemeCity.components.location.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * LocationRequest实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-17 新建
 */
public class LocationRequest {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (channel,VARCHAR(100), null)请求渠道(google,baidu,gaode) */
	private String channel;

	/** (latitude,FLOAT, null)纬度，浮点数，范围为-90~90，负数表示南纬 */
	private float latitude;

	/** (longitude,FLOAT, null)经度，浮点数，范围为-180~180，负数表示西经 */
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

	/** (country,VARCHAR(200), null)国家 */
	private String country;

	/** (city,VARCHAR(200), null)城市 */
	private String city;

	/** (address,VARCHAR(500), null)详细地址 */
	private String address;

	/** (status,VARCHAR(1), null)状态(Y:成功，N:失败) */
	private String status;

	/** (is_deleted,VARCHAR(1), null)是否已删除(Y/N) */
	private String isDeleted;

	/** (create_by,BIGINT(20), null)用户 */
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

	public void setId(long id){
		this.id=id;
	}

	public long getId(){
		return this.id;
	}

	public void setChannel(String channel){
		this.channel=channel;
	}

	public String getChannel(){
		return this.channel;
	}

	public void setLatitude(float latitude){
		this.latitude=latitude;
	}

	public float getLatitude(){
		return this.latitude;
	}

	public void setLongitude(float longitude){
		this.longitude=longitude;
	}

	public float getLongitude(){
		return this.longitude;
	}

	public void setSpeed(float speed){
		this.speed=speed;
	}

	public float getSpeed(){
		return this.speed;
	}

	public void setAccuracy(float accuracy){
		this.accuracy=accuracy;
	}

	public float getAccuracy(){
		return this.accuracy;
	}

	public void setAltitude(float altitude){
		this.altitude=altitude;
	}

	public float getAltitude(){
		return this.altitude;
	}

	public void setVerticalAccuracy(float verticalAccuracy){
		this.verticalAccuracy=verticalAccuracy;
	}

	public float getVerticalAccuracy(){
		return this.verticalAccuracy;
	}

	public void setHorizontalAccuracy(float horizontalAccuracy){
		this.horizontalAccuracy=horizontalAccuracy;
	}

	public float getHorizontalAccuracy(){
		return this.horizontalAccuracy;
	}

	public void setCountry(String country){
		this.country=country;
	}

	public String getCountry(){
		return this.country;
	}

	public void setCity(String city){
		this.city=city;
	}

	public String getCity(){
		return this.city;
	}

	public void setAddress(String address){
		this.address=address;
	}

	public String getAddress(){
		return this.address;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setIsDeleted(String isDeleted){
		this.isDeleted=isDeleted;
	}

	public String getIsDeleted(){
		return this.isDeleted;
	}

	public void setCreateBy(long createBy){
		this.createBy=createBy;
	}

	public long getCreateBy(){
		return this.createBy;
	}

	public void setCreateTime(LocalDateTime createTime){
		this.createTime=createTime;
	}

	public LocalDateTime getCreateTime(){
		return this.createTime;
	}

	public void setModifyBy(long modifyBy){
		this.modifyBy=modifyBy;
	}

	public long getModifyBy(){
		return this.modifyBy;
	}

	public void setModifyTime(LocalDateTime modifyTime){
		this.modifyTime=modifyTime;
	}

	public LocalDateTime getModifyTime(){
		return this.modifyTime;
	}

}