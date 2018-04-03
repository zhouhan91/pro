
package com.wemeCity.web.user.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * UserSession实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-26 新建
 */
public class UserSession {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (user_id,BIGINT(20), null)用户id */
	private long userId;

	/** (union_id,VARCHAR(256), null)微信开放平台唯一id */
	private String unionId;

	/** (session_key,VARCHAR(256), null)微信返回sessionkey */
	private String sessionKey;

	/** (open_id,VARCHAR(256), null)微信openid */
	private String openId;

	/** (user_key,VARCHAR(256), null)生成返回给前端key */
	private String userKey;

	/** (create_time,DATETIME, null)创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	public void setId(long id){
		this.id=id;
	}

	public long getId(){
		return this.id;
	}

	public void setUserId(long userId){
		this.userId=userId;
	}

	public long getUserId(){
		return this.userId;
	}

	public void setUnionId(String unionId){
		this.unionId=unionId;
	}

	public String getUnionId(){
		return this.unionId;
	}

	public void setSessionKey(String sessionKey){
		this.sessionKey=sessionKey;
	}

	public String getSessionKey(){
		return this.sessionKey;
	}

	public void setOpenId(String openId){
		this.openId=openId;
	}

	public String getOpenId(){
		return this.openId;
	}

	public void setUserKey(String userKey){
		this.userKey=userKey;
	}

	public String getUserKey(){
		return this.userKey;
	}

	public void setCreateTime(LocalDateTime createTime){
		this.createTime=createTime;
	}

	public LocalDateTime getCreateTime(){
		return this.createTime;
	}

}