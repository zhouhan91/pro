
package com.wemeCity.web.catering.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CateringDiscount实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
public class CateringDiscount {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (restaurant_id,BIGINT(20), null)店铺id */
	private long restaurantId;

	/** (type,VARCHAR(20), null)类型(满减：manjian，全场X折：quanchangzhekou，新人：xinren) */
	private String type;

	/** (target,DECIMAL(15,4), null)目标金额：满减有效 */
	private BigDecimal target;

	/** (discount,DECIMAL(15,4), null)优惠 */
	private BigDecimal discount;

	/** (status,VARCHAR(1), null)状态(Y：已发布，N：未发布) */
	private String status;

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

	public void setType(String type){
		this.type=type;
	}

	public String getType(){
		return this.type;
	}

	public void setTarget(BigDecimal target){
		this.target=target;
	}

	public BigDecimal getTarget(){
		return this.target;
	}

	public void setDiscount(BigDecimal discount){
		this.discount=discount;
	}

	public BigDecimal getDiscount(){
		return this.discount;
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