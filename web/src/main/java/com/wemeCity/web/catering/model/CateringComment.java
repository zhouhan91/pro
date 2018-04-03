package com.wemeCity.web.catering.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CateringComment实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2018-1-31 新建
 */
public class CateringComment {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (user_id,BIGINT(20), null)用户id */
	private long userId;

	/** (order_id,BIGINT(20), null)订单id */
	private long orderId;

	/** (restaurant_id,BIGINT(20), null)店铺id */
	private long restaurantId;

	/** (score,FLOAT, null)评分 */
	private float score;

	/** (taste_level,FLOAT, null)口味评分，10分制 */
	private float tasteLevel;

	/** (environment_level,FLOAT, null)环境评分，10分制 */
	private float environmentLevel;

	/** (service_level,FLOAT, null)服务评分，10分制 */
	private float serviceLevel;

	/** (recommend_flag,VARCHAR(1), null)是否推荐(Y/N) */
	private String recommendFlag;

	/** (content,VARCHAR(1000), null)文字内容 */
	private String content;

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

	/** 用户标识 */
	private String userKey;
	
	/** (nick_name,VARCHAR(20), null)昵称 */
	private String nickName;

	/** (portrait,VARCHAR(500), null)头像 */
	private String portrait;

	/** 评论图片 */
	private List<CateringCommentImg> lstCommentImg;

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

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public long getRestaurantId() {
		return this.restaurantId;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public float getScore() {
		return this.score;
	}

	public void setTasteLevel(float tasteLevel) {
		this.tasteLevel = tasteLevel;
	}

	public float getTasteLevel() {
		return this.tasteLevel;
	}

	public void setEnvironmentLevel(float environmentLevel) {
		this.environmentLevel = environmentLevel;
	}

	public float getEnvironmentLevel() {
		return this.environmentLevel;
	}

	public void setServiceLevel(float serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	public float getServiceLevel() {
		return this.serviceLevel;
	}

	public void setRecommendFlag(String recommendFlag) {
		this.recommendFlag = recommendFlag;
	}

	public String getRecommendFlag() {
		return this.recommendFlag;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return this.content;
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

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public List<CateringCommentImg> getLstCommentImg() {
		return lstCommentImg;
	}

	public void setLstCommentImg(List<CateringCommentImg> lstCommentImg) {
		this.lstCommentImg = lstCommentImg;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

}