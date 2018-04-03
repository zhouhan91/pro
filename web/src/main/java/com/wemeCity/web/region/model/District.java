
package com.wemeCity.web.region.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * District实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-18 新建
 */
public class District {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (code,VARCHAR(50), null)编码 */
	private String code;

	/** (name,VARCHAR(200), null)名字(当地语言) */
	private String name;

	/** (chinese_name,VARCHAR(200), null)中文名 */
	private String chineseName;

	/** (english_name,VARCHAR(200), null)英文名 */
	private String englishName;

	/** (city_id,BIGINT(20), null) */
	private long cityId;

	/** (province_id,BIGINT(20), null)省/州id */
	private long provinceId;

	/** (country_id,BIGINT(20), null)国家id */
	private long countryId;

	/** (image,VARCHAR(500), null)封面图片 */
	private String image;

	/** (area_code,VARCHAR(20), null)电话区号 */
	private String areaCode;

	/** (post_code,VARCHAR(20), null)邮政编码 */
	private String postCode;

	/** (key_words,VARCHAR(200), null)关键词，逗号分隔 */
	private String keyWords;

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

	public void setId(long id){
		this.id=id;
	}

	public long getId(){
		return this.id;
	}

	public void setCode(String code){
		this.code=code;
	}

	public String getCode(){
		return this.code;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return this.name;
	}

	public void setChineseName(String chineseName){
		this.chineseName=chineseName;
	}

	public String getChineseName(){
		return this.chineseName;
	}

	public void setEnglishName(String englishName){
		this.englishName=englishName;
	}

	public String getEnglishName(){
		return this.englishName;
	}

	public void setCityId(long cityId){
		this.cityId=cityId;
	}

	public long getCityId(){
		return this.cityId;
	}

	public void setProvinceId(long provinceId){
		this.provinceId=provinceId;
	}

	public long getProvinceId(){
		return this.provinceId;
	}

	public void setCountryId(long countryId){
		this.countryId=countryId;
	}

	public long getCountryId(){
		return this.countryId;
	}

	public void setImage(String image){
		this.image=image;
	}

	public String getImage(){
		return this.image;
	}

	public void setAreaCode(String areaCode){
		this.areaCode=areaCode;
	}

	public String getAreaCode(){
		return this.areaCode;
	}

	public void setPostCode(String postCode){
		this.postCode=postCode;
	}

	public String getPostCode(){
		return this.postCode;
	}

	public void setKeyWords(String keyWords){
		this.keyWords=keyWords;
	}

	public String getKeyWords(){
		return this.keyWords;
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