
package com.wemeCity.web.catering.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CateringGoodsCategory实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-24 新建
 */
public class CateringGoodsCategory {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (code,VARCHAR(50), null)编码 */
	private String code;

	/** (name,VARCHAR(200), null)分类名字 */
	private String name;

	/** (restaurant_id,BIGINT(20), null)店铺id */
	private long restaurantId;

	/** (parent_id,BIGINT(20), null)父分类id */
	private long parentId;

	/** (parent_code,VARCHAR(50), null)父分类编码 */
	private String parentCode;

	/** (sort_num,INT(11), null)序号/排序号(升序) */
	private int sortNum;

	/** (status,VARCHAR(1), null)状态(Y/N) */
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

	public void setRestaurantId(long restaurantId){
		this.restaurantId=restaurantId;
	}

	public long getRestaurantId(){
		return this.restaurantId;
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

	public void setSortNum(int sortNum){
		this.sortNum=sortNum;
	}

	public int getSortNum(){
		return this.sortNum;
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