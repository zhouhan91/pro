
package com.wemeCity.components.sms.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Sms实体类
 *
 * @author 
 * @since JDK1.8
 * @history 2017-6-6 新建
 */
public class Sms {
	/** (id,BIGINT(20),not null) */
	private long id;

	/** (busi_code,VARCHAR(20), null)业务编码 */
	private String busiCode;

	/** (reciver,VARCHAR(20), null)接收手机号码 */
	private String reciver;

	/** (content,VARCHAR(500), null) */
	private String content;

	/** (create_time,DATETIME, null)发送时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	/** (result,VARCHAR(1), null)短信发送结果(Y/N) */
	private String result;

	/** (is_deleted,VARCHAR(1), null)是否已删除(Y/N) */
	private String isDeleted;

	public void setId(long id){
		this.id=id;
	}

	public long getId(){
		return this.id;
	}

	public void setBusiCode(String busiCode){
		this.busiCode=busiCode;
	}

	public String getBusiCode(){
		return this.busiCode;
	}

	public void setReciver(String reciver){
		this.reciver=reciver;
	}

	public String getReciver(){
		return this.reciver;
	}

	public void setContent(String content){
		this.content=content;
	}

	public String getContent(){
		return this.content;
	}

	public void setCreateTime(LocalDateTime createTime){
		this.createTime=createTime;
	}

	public LocalDateTime getCreateTime(){
		return this.createTime;
	}

	public void setResult(String result){
		this.result=result;
	}

	public String getResult(){
		return this.result;
	}

	public void setIsDeleted(String isDeleted){
		this.isDeleted=isDeleted;
	}

	public String getIsDeleted(){
		return this.isDeleted;
	}

}