
package com.wemeCity.components.exchangeRate.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * ExchangeRate实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-16 新建
 */
public class ExchangeRate {
	/** (id,BIGINT(20),not null)主键 */
	private long id;

	/** (euros,DECIMAL(15,4), null)欧元 */
	private BigDecimal euros;

	/** (dollar,DECIMAL(15,4), null)美元 */
	private BigDecimal dollar;

	/** (yen,DECIMAL(15,4), null)日元 */
	private BigDecimal yen;

	/** (hk,DECIMAL(15,4), null)港币 */
	private BigDecimal hk;

	/** (pound,DECIMAL(15,4), null)英镑 */
	private BigDecimal pound;

	/** (update_time,DATETIME, null)更新时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateTime;

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

	public void setEuros(BigDecimal euros){
		this.euros=euros;
	}

	public BigDecimal getEuros(){
		return this.euros;
	}

	public void setDollar(BigDecimal dollar){
		this.dollar=dollar;
	}

	public BigDecimal getDollar(){
		return this.dollar;
	}

	public void setYen(BigDecimal yen){
		this.yen=yen;
	}

	public BigDecimal getYen(){
		return this.yen;
	}

	public void setHk(BigDecimal hk){
		this.hk=hk;
	}

	public BigDecimal getHk(){
		return this.hk;
	}

	public void setPound(BigDecimal pound){
		this.pound=pound;
	}

	public BigDecimal getPound(){
		return this.pound;
	}

	public void setUpdateTime(LocalDateTime updateTime){
		this.updateTime=updateTime;
	}

	public LocalDateTime getUpdateTime(){
		return this.updateTime;
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