
package com.wemeCity.web.catering.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CateringCategory实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2018-1-24 新建
 */
public class CateringCategory {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (name,VARCHAR(50), null)分类名称 */
	private String name;

	/** (code,VARCHAR(50), null)分类编码 */
	private String code;

	/** (image,VARCHAR(500), null)图标 */
	private String image;

	/** (parent_id,BIGINT(20), null)父id */
	private long parentId;

	/** (parent_code,VARCHAR(50), null)父编码 */
	private String parentCode;

	/** (status,VARCHAR(1), null)状态(Y：正常，N：不正常) */
	private String status;

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

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return this.name;
	}

	public void setCode(String code){
		this.code=code;
	}

	public String getCode(){
		return this.code;
	}

	public void setImage(String image){
		this.image=image;
	}

	public String getImage(){
		return this.image;
	}

	public void setParentId(long parentId){
		this.parentId=parentId;
	}

	public long getParentId(){
		return this.parentId;
	}

	public void setParentCode(String parentCode){
		this.parentCode=parentCode;
	}

	public String getParentCode(){
		return this.parentCode;
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