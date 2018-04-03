package com.wemeCity.web.community.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Room实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-9-19 新建
 */
public class Room implements Serializable {

	private static final long serialVersionUID = -4067381506275316996L;

	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (community_id,BIGINT(20), null)社区id */
	private long communityId;

	/** (name,VARCHAR(200), null)名称(可冗余房型，如：敲级可爱的一房一厅) */
	private String name;

	/** (type,VARCHAR(200), null)房型(一房一厅、多人房等) */
	private String type;

	/** (type_code,VARCHAR(50), null)房型字典项 */
	private String typeCode;

	/** (price,DECIMAL(15,4), null)价格 */
	private BigDecimal price;

	/** (discount_price,DECIMAL(15,4), null)折扣价 */
	private BigDecimal discountPrice;

	/** (tip_price,DECIMAL(15,4), null)定金 */
	private BigDecimal tipPrice;

	/** (deposit_price,DECIMAL(15,4), null)押金 */
	private BigDecimal depositPrice;

	/** (first_rent_month,INT(11), null)首次支付几个月租金 */
	private int firstRentMonth;

	/** (first_deposit_month,INT(11), null)首次支付几个月押金 */
	private int firstDepositMonth;

	/** (first_amount,DECIMAL(15,4), null)首次支付金额(默认为：房型价格*首次租金月份+押金*首次押金月份) */
	private BigDecimal firstAmount;

	/** (area,FLOAT(10,2), null)面积(单位平米) */
	private float area;

	/** (lease_model_key,VARCHAR(50), null)租赁类型(日/月)字典项key */
	private String leaseModelKey;

	/** (lease_model,VARCHAR(50), null)租赁类型(日/月) */
	private String leaseModel;

	/** (lease_limit,INT(11), null)最短租期 */
	private int leaseLimit;

	/** (lease_unit,VARCHAR(20), null)最短租期单位(日/月) */
	private String leaseUnit;

	/** (bed_type,VARCHAR(20), null)床型 */
	private String bedType;

	/** (bed_type_code,VARCHAR(50), null)床型字典项编码 */
	private String bedTypeCode;

	/** (date_allowed_start,DATETIME, null)可租租期-开始 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateAllowedStart;

	/** (date_allowed_end,DATETIME, null)可租租期-结束 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateAllowedEnd;

	/** (pay_flag,VARCHAR(1), null)是否需要支付(Y/N) */
	private String payFlag;

	/** (tip_flag,VARCHAR(1), null)是否需要定金(Y/N) */
	private String tipFlag;

	/** (sort_num,INT(11), null)排序字段(升序) */
	private int sortNum;

	/** (status,VARCHAR(8), null)状态(10:新建，20:已上架，30:已下架) */
	private String status;

	/** (is_deleted,VARCHAR(1),not null)是否已删除(Y/N) */
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

	/** 图片 */
	private List<CommunityImg> images;

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setCommunityId(long communityId) {
		this.communityId = communityId;
	}

	public long getCommunityId() {
		return this.communityId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeCode() {
		return this.typeCode;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public BigDecimal getDiscountPrice() {
		return this.discountPrice;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public float getArea() {
		return this.area;
	}

	public void setLeaseModel(String leaseModel) {
		this.leaseModel = leaseModel;
	}

	public String getLeaseModel() {
		return this.leaseModel;
	}

	public void setLeaseLimit(int leaseLimit) {
		this.leaseLimit = leaseLimit;
	}

	public int getLeaseLimit() {
		return this.leaseLimit;
	}

	public void setLeaseUnit(String leaseUnit) {
		this.leaseUnit = leaseUnit;
	}

	public String getLeaseUnit() {
		return this.leaseUnit;
	}

	public void setBedType(String bedType) {
		this.bedType = bedType;
	}

	public String getBedType() {
		return this.bedType;
	}

	public void setBedTypeCode(String bedTypeCode) {
		this.bedTypeCode = bedTypeCode;
	}

	public String getBedTypeCode() {
		return this.bedTypeCode;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
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

	public List<CommunityImg> getImages() {
		return images;
	}

	public void setImages(List<CommunityImg> images) {
		this.images = images;
	}

	public LocalDateTime getDateAllowedStart() {
		return dateAllowedStart;
	}

	public void setDateAllowedStart(LocalDateTime dateAllowedStart) {
		this.dateAllowedStart = dateAllowedStart;
	}

	public LocalDateTime getDateAllowedEnd() {
		return dateAllowedEnd;
	}

	public void setDateAllowedEnd(LocalDateTime dateAllowedEnd) {
		this.dateAllowedEnd = dateAllowedEnd;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public BigDecimal getTipPrice() {
		return tipPrice;
	}

	public void setTipPrice(BigDecimal tipPrice) {
		this.tipPrice = tipPrice;
	}

	public BigDecimal getDepositPrice() {
		return depositPrice;
	}

	public void setDepositPrice(BigDecimal depositPrice) {
		this.depositPrice = depositPrice;
	}

	public int getFirstRentMonth() {
		return firstRentMonth;
	}

	public void setFirstRentMonth(int firstRentMonth) {
		this.firstRentMonth = firstRentMonth;
	}

	public int getFirstDepositMonth() {
		return firstDepositMonth;
	}

	public void setFirstDepositMonth(int firstDepositMonth) {
		this.firstDepositMonth = firstDepositMonth;
	}

	public BigDecimal getFirstAmount() {
		return firstAmount;
	}

	public void setFirstAmount(BigDecimal firstAmount) {
		this.firstAmount = firstAmount;
	}

	public String getLeaseModelKey() {
		return leaseModelKey;
	}

	public void setLeaseModelKey(String leaseModelKey) {
		this.leaseModelKey = leaseModelKey;
	}

	public String getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}

	public String getTipFlag() {
		return tipFlag;
	}

	public void setTipFlag(String tipFlag) {
		this.tipFlag = tipFlag;
	}

}