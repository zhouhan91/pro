
package com.wemeCity.web.catering.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CateringCommentImg实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
public class CateringCommentImg implements Serializable {
    private static final long serialVersionUID = 8375793500664218230L;

	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (comment_id,BIGINT(20), null)评论id */
	private long commentId;

	/** (user_id,BIGINT(20), null)用户id */
	private long userId;

	/** (restaurant_id,BIGINT(20), null)店铺id */
	private long restaurantId;

	/** (path,VARCHAR(500), null)路径 */
	private String path;

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

	public void setCommentId(long commentId){
		this.commentId=commentId;
	}

	public long getCommentId(){
		return this.commentId;
	}

	public void setUserId(long userId){
		this.userId=userId;
	}

	public long getUserId(){
		return this.userId;
	}

	public void setRestaurantId(long restaurantId){
		this.restaurantId=restaurantId;
	}

	public long getRestaurantId(){
		return this.restaurantId;
	}

	public void setPath(String path){
		this.path=path;
	}

	public String getPath(){
		return this.path;
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