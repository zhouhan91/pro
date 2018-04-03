package com.wemeCity.web.catering.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CateringRecommend实体类
 *
 * @author 向小文
 * @since JDK1.8
 * @history 2017-12-5 新建
 */
public class CateringRecommend {
	/** (id,BIGINT(20),not null)自增主键 */
	private long id;

	/** (city_code,VARCHAR(50), null)城市编码 */
	private String cityCode;

	/** (city_name,VARCHAR(200), null)城市名字 */
	private String cityName;

	/** (district_code,VARCHAR(50), null)区县编码 */
	private String districtCode;

	/** (district_name,VARCHAR(200), null)区县名字 */
	private String districtName;

	/** (title,VARCHAR(200), null)推荐广告标题 */
	private String title;

	/** (goods_name,VARCHAR(100), null)商品名字 */
	private String goodsName;

	/** (img,VARCHAR(500), null)图片地址 */
	private String img;

	/** (price,DECIMAL(15,4), null, default:0.0000)原价 */
	private BigDecimal price;

	/** (discount_price,DECIMAL(15,4), null)折扣价 */
	private BigDecimal discountPrice;

	/** (url,VARCHAR(500), null)跳转的URL */
	private String url;

	/** (status,VARCHAR(1), null)状态(Y：上架，N：下架) */
	private String status;

	/** (sort_num,INT(11), null)排序号 */
	private int sortNum;

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

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictCode() {
		return this.districtCode;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictName() {
		return this.districtName;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getImg() {
		return this.img;
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

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public int getSortNum() {
		return this.sortNum;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getIsDeleted() {
		return this.isDeleted;
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
}