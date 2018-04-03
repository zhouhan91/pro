
package com.wemeCity.web.user.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Coupon实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-28 新建
 */
public class Coupon {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (user_id,BIGINT(20), null)用户id */
	private long userId;

	/** (activity_id,BIGINT(20), null)活动id */
	private long activityId;

	/** (activity_name,BIGINT(20), null)活动名称 */
	private String activityName;



	/** (busi_code,VARCHAR(50), null)业务类型 */
	private String busiCode;

	/** (busi_id,BIGINT(20), null)使用所关联的业务订单id */
	private long busiId;

	/** (amount,DECIMAL(15,4), null)金额 */
	private BigDecimal amount;

	/** (status,VARCHAR(1), null)状态(1:未使用，2：已使用) */
	private String status;

	/** (remark,VARCHAR(200), null)备注 */
	private String remark;

	/** (user_time,DATETIME, null)使用时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime userTime;

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

	public void setUserId(long userId){
		this.userId=userId;
	}

	public long getUserId(){
		return this.userId;
	}

	public void setBusiCode(String busiCode){
		this.busiCode=busiCode;
	}

	public String getBusiCode(){
		return this.busiCode;
	}

	public void setBusiId(long busiId){
		this.busiId=busiId;
	}

	public long getBusiId(){
		return this.busiId;
	}

	public void setAmount(BigDecimal amount){
		this.amount=amount;
	}

	public BigDecimal getAmount(){
		return this.amount;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setUserTime(LocalDateTime userTime){
		this.userTime=userTime;
	}

	public LocalDateTime getUserTime(){
		return this.userTime;
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