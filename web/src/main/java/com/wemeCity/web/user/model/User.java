package com.wemeCity.web.user.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * User实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-26 新建
 */
public class User {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (open_id,VARCHAR(256), null)微信小程序对应用户openid */
	private String openId;

	/** (union_id,VARCHAR(256), null)微信开放平台唯一id */
	private String unionId;

	/** (phone,VARCHAR(20), null)手机 */
	private String phone;

	/** (password,VARCHAR(50), null)密码 */
	private String password;

	/** (nick_name,VARCHAR(20), null)昵称 */
	private String nickName;

	/** (portrait,VARCHAR(500), null)头像 */
	private String portrait;

	/** (sex,VARCHAR(2), null)性别 */
	private String sex;

	/** (birthday,DATE, null)生日 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;

	/** (country,VARCHAR(50), null)国家 */
	private String country;

	/** (country_id,BIGINT(20), null)国家id */
	private long countryId;

	/** (province,VARCHAR(50), null)省份 */
	private String province;

	/** (province_id,BIGINT(20), null)省份id */
	private long provinceId;

	/** (city,VARCHAR(50), null)城市 */
	private String city;

	/** (city_id,BIGINT(20), null)城市id */
	private long cityId;

	/** (district,VARCHAR(50), null)区县 */
	private String district;

	/** (district_id,BIGINT(20), null)区县id */
	private long districtId;

	/** (address,VARCHAR(200), null)地址 */
	private String address;

	/** (mail,VARCHAR(200), null)邮件 */
	private String mail;

	/** (qq,VARCHAR(50), null)qq */
	private String qq;

	/** (wechat,VARCHAR(50), null)微信号 */
	private String wechat;

	/** (facebook,VARCHAR(500), null)facebook账号 */
	private String facebook;

	/** (instagram,VARCHAR(500), null)instagram */
	private String instagram;

	/** (status,VARCHAR(1), null)状态(Y:可用，N:禁用) */
	private String status;

	/** (is_deleted,VARCHAR(1), null)是否删除(Y/N) */
	private String isDeleted;

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

	/** 是否新用户 */
	private String newUser;

	/** userKey 传递前端唯一用户标识 */
	private String userKey;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getUnionId() {
		return this.unionId;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getPortrait() {
		return this.portrait;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSex() {
		return this.sex;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public LocalDate getBirthday() {
		return this.birthday;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}

	public long getCountryId() {
		return this.countryId;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvinceId(long provinceId) {
		this.provinceId = provinceId;
	}

	public long getProvinceId() {
		return this.provinceId;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return this.city;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public long getCityId() {
		return this.cityId;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}

	public long getDistrictId() {
		return this.districtId;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMail() {
		return this.mail;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getQq() {
		return this.qq;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getWechat() {
		return this.wechat;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getFacebook() {
		return this.facebook;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getInstagram() {
		return this.instagram;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getIsDeleted() {
		return this.isDeleted;
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

	public String getNewUser() {
		return newUser;
	}

	public void setNewUser(String newUser) {
		this.newUser = newUser;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

}