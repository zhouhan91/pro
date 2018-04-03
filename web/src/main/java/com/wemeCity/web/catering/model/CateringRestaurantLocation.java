
package com.wemeCity.web.catering.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CateringRestaurantLocation实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-8 新建
 */
public class CateringRestaurantLocation {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (restaurant_id,BIGINT(20), null)店铺id */
	private long restaurantId;

	/** (city_code,VARCHAR(50), null)城市编码 */
	private String cityCode;

	/** (city_name,VARCHAR(200), null)城市名字 */
	private String cityName;

	/** (district_code,VARCHAR(50), null)区县编码 */
	private String districtCode;

	/** (district_name,VARCHAR(200), null)区县名字 */
	private String districtName;

	/** (address,VARCHAR(500), null)具体地址 */
	private String address;

	/** (is_deleted,VARCHAR(255), null)是否已删除(Y/N) */
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

	public void setId(long id){
		this.id=id;
	}

	public long getId(){
		return this.id;
	}

	public void setRestaurantId(long restaurantId){
		this.restaurantId=restaurantId;
	}

	public long getRestaurantId(){
		return this.restaurantId;
	}

	public void setCityCode(String cityCode){
		this.cityCode=cityCode;
	}

	public String getCityCode(){
		return this.cityCode;
	}

	public void setCityName(String cityName){
		this.cityName=cityName;
	}

	public String getCityName(){
		return this.cityName;
	}

	public void setDistrictCode(String districtCode){
		this.districtCode=districtCode;
	}

	public String getDistrictCode(){
		return this.districtCode;
	}

	public void setDistrictName(String districtName){
		this.districtName=districtName;
	}

	public String getDistrictName(){
		return this.districtName;
	}

	public void setAddress(String address){
		this.address=address;
	}

	public String getAddress(){
		return this.address;
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