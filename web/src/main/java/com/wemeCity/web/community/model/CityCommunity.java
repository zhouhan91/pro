package com.wemeCity.web.community.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CityCommunity实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-16 新建
 */
public class CityCommunity {
	/** (id,BIGINT(20),not null) */
	private long id;

	/** (city_id,BIGINT(20), null)城市 */
	private long cityId;

	/** (city_code,VARCHAR(50), null)城市编码 */
	private String cityCode;

	/** (city_name,VARCHAR(200), null)城市名称(中文) */
	private String cityName;

	/** (country_id,BIGINT(20), null)国家 */
	private long countryId;

	/** (country_code,VARCHAR(50), null)国家编码 */
	private String countryCode;

	/** (country_name,VARCHAR(200), null)国家名字(中文) */
	private String countryName;

	/** (image,VARCHAR(500), null)图片 */
	private String image;

	/** (community_count,INT(11), null)社区数 */
	private int communityCount;

	/** (room_count,INT(11), null)房源数 */
	private int roomCount;

	/** (experience_count,INT(11), null)入住人数 */
	private int experienceCount;

	/** (comment_count,INT(11), null)评论数 */
	private int commentCount;

	/** (sort_num,INT(11), null)排序字段(升序) */
	private int sortNum;

	/** (is_deleted,VARCHAR(1), null)是否删除(Y/N) */
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

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public long getCityId() {
		return this.cityId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}

	public long getCountryId() {
		return this.countryId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return this.image;
	}

	public void setCommunityCount(int communityCount) {
		this.communityCount = communityCount;
	}

	public int getCommunityCount() {
		return this.communityCount;
	}

	public void setRoomCount(int roomCount) {
		this.roomCount = roomCount;
	}

	public int getRoomCount() {
		return this.roomCount;
	}

	public void setExperienceCount(int experienceCount) {
		this.experienceCount = experienceCount;
	}

	public int getExperienceCount() {
		return this.experienceCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getCommentCount() {
		return this.commentCount;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public int getSortNum() {
		return this.sortNum;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getIsDeleted() {
		return this.isDeleted;
	}

	public void setCreateBy(long createBy) {
		this.createBy = createBy;
	}

	public long getCreateBy() {
		return this.createBy;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getCreateTime() {
		return this.createTime;
	}

	public void setModifyBy(long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public long getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyTime(LocalDateTime modifyTime) {
		this.modifyTime = modifyTime;
	}

	public LocalDateTime getModifyTime() {
		return this.modifyTime;
	}

}