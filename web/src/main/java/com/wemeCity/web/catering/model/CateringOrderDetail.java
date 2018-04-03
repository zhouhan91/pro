
package com.wemeCity.web.catering.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CateringOrderDetail实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
public class CateringOrderDetail implements Serializable {
	
    private static final long serialVersionUID = -239069267558937595L;

	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (order_id,BIGINT(20), null)订单id */
	private long orderId;

	/** (user_id,BIGINT(20), null)用户id */
	private long userId;

	/** (goods_id,BIGINT(20), null)商品id */
	private long goodsId;

	/** (goods_name,VARCHAR(255), null)商品名称 */
	private String goodsName;

	/** (price,DECIMAL(15,4), null)商品单价 */
	private BigDecimal price;

	/** (discount_price,DECIMAL(15,4), null)商品折扣价 */
	private BigDecimal discountPrice;

	/** (count,INT(11), null)商品数量 */
	private int count;

	/** (amount,DECIMAL(15,4), null)商品总价 */
	private BigDecimal amount;

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

	public void setOrderId(long orderId){
		this.orderId=orderId;
	}

	public long getOrderId(){
		return this.orderId;
	}

	public void setUserId(long userId){
		this.userId=userId;
	}

	public long getUserId(){
		return this.userId;
	}

	public void setGoodsId(long goodsId){
		this.goodsId=goodsId;
	}

	public long getGoodsId(){
		return this.goodsId;
	}

	public void setGoodsName(String goodsName){
		this.goodsName=goodsName;
	}

	public String getGoodsName(){
		return this.goodsName;
	}

	public void setPrice(BigDecimal price){
		this.price=price;
	}

	public BigDecimal getPrice(){
		return this.price;
	}

	public void setDiscountPrice(BigDecimal discountPrice){
		this.discountPrice=discountPrice;
	}

	public BigDecimal getDiscountPrice(){
		return this.discountPrice;
	}

	public void setCount(int count){
		this.count=count;
	}

	public int getCount(){
		return this.count;
	}

	public void setAmount(BigDecimal amount){
		this.amount=amount;
	}

	public BigDecimal getAmount(){
		return this.amount;
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